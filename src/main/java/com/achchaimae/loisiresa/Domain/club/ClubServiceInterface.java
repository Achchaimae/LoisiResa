package com.achchaimae.loisiresa.Domain.club;

import com.achchaimae.loisiresa.Domain.club.dto.ClubReqDTO;
import com.achchaimae.loisiresa.Domain.club.dto.ClubRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubServiceInterface {
    Page<ClubRespDTO> getClubs(Pageable pageable);
    ClubRespDTO findClub(Integer ClubId);
    ClubRespDTO saveClub(ClubReqDTO club);
    ClubRespDTO updateClub(ClubReqDTO club, Integer id);
    Integer DeleteClub(Integer clubId);
}
