package com.achchaimae.loisiresa.security.auth;


import com.achchaimae.loisiresa.Domain.user.contact.Contact;
import com.achchaimae.loisiresa.Domain.user.contact.ContactRepository;
import com.achchaimae.loisiresa.Domain.user.guide.Guide;
import com.achchaimae.loisiresa.Domain.user.guide.GuideRepository;
import com.achchaimae.loisiresa.Exception.ResourceNotFoundException;
import com.achchaimae.loisiresa.security.user.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import com.achchaimae.loisiresa.security.user.dto.UserReqDTO;
import com.achchaimae.loisiresa.security.user.User;
import com.achchaimae.loisiresa.security.user.UserRepository;
import com.achchaimae.loisiresa.security.jwt.JwtService;
import com.achchaimae.loisiresa.security.token.Token;
import com.achchaimae.loisiresa.security.token.TokenRepository;
import com.achchaimae.loisiresa.security.token.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final ContactRepository contactRepository;
    private final GuideRepository guideRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;

    public AuthenticationResponse register(UserReqDTO request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .identityNum(request.getIdentityNum())
                .identityDocumentType(request.getIdentityDocumentType())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .requestStatus(request.getRequestStatus())
                .requestedRole(request.getRequestedRole())
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(modelMapper.map(savedUser, UserRespDTO.class))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .user(modelMapper.map(user, UserRespDTO.class))
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }

    }

    public Optional<List<User>> getUsersByStatus(Integer status) {
        return repository.findByRequestStatus(status);
    }



    @Transactional
    public AuthenticationResponse updateUser(Integer userId, UserReqDTO updatedUserData) {
        var user = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user data with the new values
        user.setFirstName(updatedUserData.getFirstName());
        user.setLastName(updatedUserData.getLastName());
        user.setEmail(updatedUserData.getEmail());
        user.setDateOfBirth(updatedUserData.getDateOfBirth());
        user.setIdentityNum(updatedUserData.getIdentityNum());
        user.setIdentityDocumentType(updatedUserData.getIdentityDocumentType());
        user.setAddress(updatedUserData.getAddress());
        user.setPassword(passwordEncoder.encode(updatedUserData.getPassword()));
        user.setRequestStatus(updatedUserData.getRequestStatus());
        user.setRequestedRole(updatedUserData.getRequestedRole());
        user.setRole(updatedUserData.getRole());

        var updatedUser = repository.save(user);

        return AuthenticationResponse.builder()
                .user(modelMapper.map(updatedUser, UserRespDTO.class))
                .build();
    }




    public UserRespDTO updateUserRole(Integer userId, Role newRole) {
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Role currentRole = user.getRole();
            if (user.getRequestedRole().equals(Role.client)){
                updateRequestedStatus(userId, 2);
            }
            // Check if the current role is different from the newRole
            if (!currentRole.equals(newRole)) {
                user.setRole(newRole);
                repository.save(user);

                // Check if the updated role is now the same as the requested role
                if (user.getRole().equals(newRole)) {
                    // Update the requested status to 2
                    updateRequestedStatus(userId, 2);
                }
//
//                // Check if the status is 2, then add the user to the guide table
//                if (user.getRequestedRole() == Role.guide) {
//
//                }
//
//                if (user.getRequestedRole() == Role.contact) {
//                    addToContactTable(user);
//                }


                    return modelMapper.map(user, UserRespDTO.class);
            } else {
                // Return the existing user response without updating anything
                return modelMapper.map(user, UserRespDTO.class);
            }
        } else {
            throw new ResourceNotFoundException("Member with id " + userId + " not found.");
        }
    }

    @Transactional
    public void refuseRequest(Integer userId) {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        // Set the requested status to 0 (refused)
        user.setRequestStatus(0);

        repository.save(user);
    }
    private void updateRequestedStatus(Integer userId, int status) {
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRequestStatus(status);
            repository.save(user);
        } else {
            throw new ResourceNotFoundException("Member with id " + userId + " not found.");
        }
    }


    private void addToContactTable(User user) {
        Contact contact = new Contact();
        contact.setFirstName(user.getFirstName());
        contact.setLastName(user.getLastName());
        contact.setEmail(user.getEmail());
        contact.setFirstDateContact(LocalDate.now());
        contact.setIdentityNum(user.getIdentityNum());
        contact.setDateOfBirth(user.getDateOfBirth());
        contact.setIdentityDocumentType(user.getIdentityDocumentType());
        contact.setAddress(user.getAddress());
        contact.setPassword(passwordEncoder.encode(user.getPassword()));
        contact.setRequestStatus(user.getRequestStatus());
        contact.setRequestedRole(user.getRequestedRole());
        contact.setRole(user.getRole());

        contactRepository.save(contact);
    }
    private void addToGuideTable(User user) {
        Guide guide = new Guide();
        guide.setFirstName(user.getFirstName());
        guide.setLastName(user.getLastName());
        guide.setEmail(user.getEmail());
        guide.setDateOfSubscription(LocalDate.now());
        guide.setDateOfBirth(user.getDateOfBirth());
        guide.setIdentityNum(user.getIdentityNum());
        guide.setIdentityDocumentType(user.getIdentityDocumentType());
        guide.setAddress(user.getAddress());
        guide.setPassword(passwordEncoder.encode(user.getPassword()));
        guide.setRequestStatus(user.getRequestStatus());
        guide.setRequestedRole(user.getRequestedRole());
        guide.setRole(user.getRole());

        guideRepository.save(guide);
    }



}