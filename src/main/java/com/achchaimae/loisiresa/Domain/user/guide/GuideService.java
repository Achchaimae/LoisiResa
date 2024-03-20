package com.achchaimae.loisiresa.Domain.user.guide;

import com.achchaimae.loisiresa.Domain.club.Club;
import com.achchaimae.loisiresa.Domain.club.ClubRepository;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideReqDTO;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideRespDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class GuideService implements GuideServiceInterface{
    public GuideService(GuideRepository guideRepository, ClubRepository clubRepository, ModelMapper modelMapper) {
        this.guideRepository = guideRepository;
        this.clubRepository = clubRepository;
        this.modelMapper = modelMapper;
    }

    GuideRepository guideRepository;
    ClubRepository clubRepository;
    ModelMapper modelMapper;

    // create a guide
    public GuideRespDTO saveGuide(GuideReqDTO guide)
    {
        guide.setDateOfSubscription(LocalDate.now() );
        return modelMapper.map(
                guideRepository.save(modelMapper.map(guide, Guide.class)),
                GuideRespDTO.class
        );
    }
    // delete a guide
    public Integer deleteGuide(Integer id)
    {
        Optional<Guide> guideOptional = guideRepository.findById(id);
        return guideOptional.map(guide -> {
            guideRepository.delete(guideOptional.get());
            return 1;
        }).orElse(0);
    }

    // get one guide
    public GuideRespDTO getGuide(Integer id) {
        Optional<Guide> guide = guideRepository.findById(id);
        return modelMapper.map(guide.orElse(null), GuideRespDTO.class);
    }

    // update  guide
    public GuideRespDTO updateGuide(Integer id,GuideReqDTO guide)
    {
        Optional<Guide> guideOptional = guideRepository.findById(id);
        return guideOptional.map(guide1 -> {
            guide.setId(id);
            return modelMapper.map(
                    guideRepository.save(modelMapper.map(guide,Guide.class)),
                    GuideRespDTO.class
            );
        }).orElse(null);
    }

    // get all guide , paginated
    public Page<GuideRespDTO> getAll(Pageable pageable)
    {
        Page<Guide> entityPage = guideRepository.findAll(pageable);
        return entityPage.map(entity -> modelMapper.map(entity,GuideRespDTO.class));
    }
    public GuideRespDTO addGuideToClub(Integer guideId, Integer clubId) {
        Optional<Guide> guideOptional = guideRepository.findById(guideId);
        Optional<Club> clubOptional = clubRepository.findById(clubId);

        if (guideOptional.isPresent() && clubOptional.isPresent()) {
            Guide guide = guideOptional.get();
            Club club = clubOptional.get();

            guide.setClub(club);
            guide.setDateOfSubscription(LocalDate.now()); // Set subscription date

            // Save the updated guide with club association
            Guide savedGuide = guideRepository.save(guide);

            return modelMapper.map(savedGuide, GuideRespDTO.class);
        } else {
            throw new IllegalArgumentException("Guide or club not found");
        }
    }


}
