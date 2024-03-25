package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.activity.ActivityRepository;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationIDReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationRespDTO;
import com.achchaimae.loisiresa.Domain.reservation.enumeration.Etat;
import com.achchaimae.loisiresa.Domain.user.client.Client;
import com.achchaimae.loisiresa.Domain.user.client.ClientRepository;
import com.achchaimae.loisiresa.Domain.user.guide.Guide;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;
    private final ActivityRepository activityRepository;

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ReservationService(ReservationRepository reservationRepository, ActivityRepository activityRepository, ClientRepository clientRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.activityRepository = activityRepository;
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }
    ReservationID reservationID = new ReservationID();
    Optional<Client> client;
    Optional<Activity> activity;
    public   List<ReservationRespDTO> getReservations(){
        return reservationRepository.findAll().stream().map(reservation -> modelMapper.map(reservation, ReservationRespDTO.class)).collect(Collectors.toList());
    }
@Transactional
public ReservationRespDTO saveReservation(ReservationReqDTO reservationReqDTO) {
    // Map ReservationReqDTO to Reservation entity
    Reservation reservation = modelMapper.map(reservationReqDTO, Reservation.class);

    // Find client and activity by IDs
    Optional<Client> clientOptional = clientRepository.findById(reservationReqDTO.getId().getClient_id());
    Optional<Activity> activityOptional = activityRepository.findById(reservationReqDTO.getId().getActivity_id());

    // Check if both client and activity exist
    if (clientOptional.isPresent() && activityOptional.isPresent()) {
        // Set client and activity for the reservation
        reservation.setId(new ReservationID(clientOptional.get(), activityOptional.get()));

        // Save the reservation entity
        reservation = reservationRepository.save(reservation);

        // Map the saved reservation to DTO and return
        return modelMapper.map(reservation, ReservationRespDTO.class);
    } else {
        // Handle case when client or activity is not found
        throw new IllegalArgumentException("Client or Activity not found with the given IDs");
    }
}
    public Integer Delete(ReservationIDReqDTO reservationIDReq){
        Client client1 = new Client();
        Activity activity1 = new Activity();

        client1.setId(reservationIDReq.getClient_id());
        activity1.setId(reservationIDReq.getActivity_id());

        Optional<Reservation> exist = reservationRepository.findById_ActivityAndId_Client(client1,activity1);

        if(exist.isPresent()){
            reservationRepository.delete(exist.get());
            return 1;
        }
        return 0;
    }

    public ReservationRespDTO findReservation(Integer reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            return modelMapper.map(reservation, ReservationRespDTO.class);
        }
        return null;
    }
    public List<ReservationRespDTO> findReservationsByClubId(Integer clubId) {
        // Get all reservations
        List<Reservation> allReservations = reservationRepository.findAll();

        // Filter reservations by club ID
        List<Reservation> filteredReservations = allReservations.stream()
                .filter(reservation -> reservation.getId().getActivity().getClub().getId()==(clubId))
                .toList();

        // Map filtered reservations to DTOs and return
        return filteredReservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationRespDTO.class))
                .collect(Collectors.toList());
    }
    public ReservationRespDTO acceptReservation(Integer clientId, Integer activityId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Optional<Activity> activityOptional = activityRepository.findById(activityId);

        if (clientOptional.isPresent() && activityOptional.isPresent()) {
            ReservationID reservationId = new ReservationID(clientOptional.get(), activityOptional.get());
            Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);

            if (reservationOptional.isPresent()) {
                Reservation reservation = reservationOptional.get();
                reservation.setEtat(Etat.CONFIRME);
                Reservation savedReservation = reservationRepository.save(reservation);
                return modelMapper.map(savedReservation, ReservationRespDTO.class);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client or Activity not found");
        }
    }

    public ReservationRespDTO refuseReservation(Integer clientId, Integer activityId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        Optional<Activity> activityOptional = activityRepository.findById(activityId);

        if (clientOptional.isPresent() && activityOptional.isPresent()) {
            ReservationID reservationId = new ReservationID(clientOptional.get(), activityOptional.get());
            Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);

            if (reservationOptional.isPresent()) {
                Reservation reservation = reservationOptional.get();
                reservation.setEtat(Etat.ANUULE);  // Assuming Etat is an enum with ANNULLE value
                Reservation savedReservation = reservationRepository.save(reservation);
                return modelMapper.map(savedReservation, ReservationRespDTO.class);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client or Activity not found");
        }
    }

    public List<ReservationRespDTO> getReservationByGuideId(Integer guideId) {
        // Get all reservations
        List<Reservation> allReservations = reservationRepository.findAll();

        // Filter reservations by guide ID
        List<Reservation> filteredReservations = allReservations.stream()
                .filter(reservation -> {
                    List<Guide> guideList = reservation.getId().getActivity().getGuideList();
                    return guideList != null && guideList.stream().anyMatch(guide -> guide.getId().equals(guideId));
                })
                .toList();

        // Map filtered reservations to DTOs and return
        return filteredReservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationRespDTO.class))
                .collect(Collectors.toList());
    }
}

