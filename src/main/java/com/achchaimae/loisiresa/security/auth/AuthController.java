package com.achchaimae.loisiresa.security.auth;


import com.achchaimae.loisiresa.Exception.ResourceNotFoundException;
import com.achchaimae.loisiresa.security.user.Role;
import com.achchaimae.loisiresa.security.user.User;
import com.achchaimae.loisiresa.security.user.dto.UserReqDTO;
import com.achchaimae.loisiresa.security.user.dto.UserRespDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService service;
    private final ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserReqDTO request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }
    @GetMapping("/user/{status}")
    public ResponseEntity<List<UserRespDTO>> getUsersByStatus(@PathVariable Integer status) {
        Optional<List<User>> usersOptional = service.getUsersByStatus(status);
        if (usersOptional.isPresent()) {
            List<User> users = usersOptional.get();
            List<UserRespDTO> userRespDTOs = users.stream()
                    .map(user -> modelMapper.map(user, UserRespDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userRespDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/user/{userId}")
    public ResponseEntity<AuthenticationResponse> updateUser(
            @PathVariable Integer userId,
            @RequestBody UserReqDTO updatedUserData
    ) {
        AuthenticationResponse response = service.updateUser(userId, updatedUserData);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/{userId}/refuse")
    public ResponseEntity<Void> refuseRequest(@PathVariable Integer userId) {
        service.refuseRequest(userId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{userId}/role")
    public ResponseEntity<UserRespDTO> updateUserRole(@PathVariable Integer userId, @RequestParam Role newRole) {
        try {
            UserRespDTO updatedUser = service.updateUserRole(userId, newRole);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }



}