package com.achchaimae.loisiresa.Domain.user.guide;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideReqDTO;
import com.achchaimae.loisiresa.Domain.user.guide.dto.GuideRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guide")
public class GuideController {
    GuideServiceInterface guideServiceInterface;
    public GuideController(GuideServiceInterface guideServiceInterface) {
        this.guideServiceInterface = guideServiceInterface;
    }


    @PostMapping
    public ResponseEntity<GuideRespDTO> createGuide(@RequestBody GuideReqDTO guide)
    {
        GuideRespDTO guide1 = guideServiceInterface.saveGuide(guide);
        if(guide1 != null)
        {
            return ResponseEntity.ok().body(guide1);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @PutMapping("/{id}")
    public ResponseEntity<GuideRespDTO> updateGuide(@PathVariable Integer id, @RequestBody GuideReqDTO guide)
    {
        GuideRespDTO guide1 = guideServiceInterface.updateGuide(id,guide);
        if(guide1 != null)
        {
            return ResponseEntity.ok().body(guide1);
        }
        return ResponseEntity.badRequest().body(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GuideRespDTO> getGuide(@PathVariable Integer id)
    {
        return ResponseEntity.ok().body(guideServiceInterface.getGuide(id));
    }
    @GetMapping
    public ResponseEntity<Page<GuideRespDTO>> getGuides(Pageable pageable)
    {
        return ResponseEntity.ok().body(guideServiceInterface.getAll(pageable));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteGuide(@PathVariable Integer id)
    {
        Integer deleted = guideServiceInterface.deleteGuide(id);
        if(deleted == 1){
            return ResponseEntity.ok().body("Guide deleted ?");
        }
        return ResponseEntity.ok().body("Guide not deleted ?");
    }
    @PostMapping("/add-to-club")
    public ResponseEntity<GuideRespDTO> addGuideToClub(
            @RequestParam Integer guideId,
            @RequestParam Integer clubId
    ) {
        GuideRespDTO guideRespDTO = guideServiceInterface.addGuideToClub(guideId, clubId);
        return ResponseEntity.status(HttpStatus.CREATED).body(guideRespDTO);
    }

}
