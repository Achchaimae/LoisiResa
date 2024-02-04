package com.achchaimae.loisiresa.Domain.user.guide;

import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideReqDTO;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface GuideServiceInterface {
    GuideRespDTO saveGuide(GuideReqDTO guide);
    Integer deleteGuide(Integer id);
    GuideRespDTO getGuide(Integer id);
    GuideRespDTO updateGuide(Integer id,GuideReqDTO guide);
    Page<GuideRespDTO> getAll(Pageable pageable);

}
