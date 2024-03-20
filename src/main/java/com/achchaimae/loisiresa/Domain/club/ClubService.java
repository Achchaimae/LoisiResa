package com.achchaimae.loisiresa.Domain.club;


import com.achchaimae.loisiresa.Domain.club.dto.ClubReqDTO;
import com.achchaimae.loisiresa.Domain.club.dto.ClubRespDTO;
import com.achchaimae.loisiresa.Exception.RecordAlreadyExistsException;
import com.achchaimae.loisiresa.Exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubService implements ClubServiceInterface{
    ClubRepository clubRepository;
    private final ModelMapper modelMapper;


    public ClubService(ClubRepository clubRepository,ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.clubRepository =clubRepository;
    }
    public Page<ClubRespDTO> getClubs(Pageable pageable){
        Page<Club> entityPage = clubRepository.findAll(pageable);
        return  entityPage.map(entity -> modelMapper.map(entity, ClubRespDTO.class));
    }

    public ClubRespDTO findClub(Integer ClubId){
        Optional<Club> clubOptional= clubRepository.findById(ClubId);
        if(clubOptional.isPresent()){
            Club club= clubOptional.get();
            return  modelMapper.map(club, ClubRespDTO.class);
        }
        return null;
    }

    public ClubRespDTO saveClub(ClubReqDTO club){
        if(clubRepository.existsById(club.getId())){
            throw new RecordAlreadyExistsException("Club with Id "+club.getId()+ "already exists.");
        }
        return modelMapper.map(clubRepository.save(modelMapper.map(club, Club.class)), ClubRespDTO.class);
    }

    public ClubRespDTO updateClub(ClubReqDTO club, Integer id){
        Optional<Club> clubOptional= clubRepository.findById(id);
        if(clubOptional.isPresent()){
            Club existClub = clubOptional.get();
            if(existClub.getId()!=club.getId() && clubRepository.existsById(club.getId())){
                throw new RecordAlreadyExistsException("Club with num " + club.getId() + " already exists.");
            }
            club.setId(existClub.getId());
            return  modelMapper.map(clubRepository.save(modelMapper.map(club,Club.class)), ClubRespDTO.class);
        }else {
            throw new ResourceNotFoundException("Club with id " + id + " not found.");
        }
    }

    public Integer DeleteClub(Integer clubId){
        Optional<Club> exist = clubRepository.findById(clubId);
        if(exist.isPresent()){
            clubRepository.deleteById(clubId);
            return 1;
        }
        return 0;
    }
    public Page<ClubRespDTO> getClubsByStatusPending(Pageable pageable) {
        Page<Club> entityPage = clubRepository.findByStatus(Status.Pending, pageable);
        return entityPage.map(entity -> modelMapper.map(entity, ClubRespDTO.class));
    }


    public ClubRespDTO acceptRequest(Integer id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found with id: " + id));

        club.setStatus(Status.Accepted);
        clubRepository.save(club);

        return modelMapper.map(club, ClubRespDTO.class);
    }



    public ClubRespDTO refuseRequest(Integer id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club not found with id: " + id));

        club.setStatus(Status.Rejected);
        clubRepository.save(club);

        return modelMapper.map(club, ClubRespDTO.class);
    }
    public ClubRespDTO getAcceptedClubByOwner(Integer ownerId) {
        Club club = clubRepository.findByOwnerIdAndStatus(ownerId, Status.Accepted);
        if (club != null) {
            return modelMapper.map(club, ClubRespDTO.class);
        } else {
            throw new ResourceNotFoundException("No accepted club found for owner with ID: " + ownerId);
        }
    }


}
