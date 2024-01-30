package com.achchaimae.loisiresa.Domain.reservation;

import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.activity.ActivityRepository;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationIDReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationReqDTO;
import com.achchaimae.loisiresa.Domain.reservation.dto.ReservationRespDTO;
import com.achchaimae.loisiresa.Domain.user.client.Client;
import com.achchaimae.loisiresa.Domain.user.client.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService implements ReservationServiceInterface {

    private final ReservationRepository reservationRepository;
    private final ActivityRepository activityRepository;

    private final ClientRepository clientRepository;
    private ModelMapper modelMapper;

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
    public ReservationRespDTO saveReservation(ReservationReqDTO reservationReqDTO) {
        Reservation reservation = modelMapper.map(reservationReqDTO, Reservation.class);
        client = clientRepository.findById(reservationReqDTO.getId().getClient_id());
        activity = activityRepository.findById(reservationReqDTO.getId().getActivity_id());
        if(activity.isPresent() && client.isPresent()){
            return modelMapper.map(reservation, ReservationRespDTO.class);
        }
        return null;
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
}

