package com.achchaimae.loisiresa.Domain.club;


import com.achchaimae.loisiresa.Domain.club.dto.ClubReqDTO;
import com.achchaimae.loisiresa.Domain.club.dto.ClubRespDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {

    ClubServiceInterface clubServiceInterface;
    ModelMapper modelMapper;

    public ClubController(ClubServiceInterface clubServiceInterface, ModelMapper modelMapper){
        this.clubServiceInterface = clubServiceInterface;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public ResponseEntity<Page<ClubRespDTO>> getClubs(Pageable pageable) {

        return ResponseEntity.ok().body(clubServiceInterface.getClubs(pageable));
    }
    @PostMapping
    public ResponseEntity<ClubRespDTO> save(@Valid @RequestBody ClubReqDTO club){
        ClubRespDTO clubResp = clubServiceInterface.saveClub(club);
        return ResponseEntity.ok().body(clubResp);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClubRespDTO> update(@PathVariable Integer id, @RequestBody ClubReqDTO club){
        return ResponseEntity.ok().body(clubServiceInterface.updateClub(club, id));
    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<ClubRespDTO> getClubById(@PathVariable Integer id){
        return ResponseEntity.ok().body(clubServiceInterface.findClub(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClub(@PathVariable Integer id){
        Integer deleted= clubServiceInterface.DeleteClub(id);
        if(deleted == 1)
        {
            return ResponseEntity.ok().body("Club delete");
        }
        return ResponseEntity.badRequest().body("Club not deleted");
    }

    @GetMapping("/pending")
    public ResponseEntity<Page<ClubRespDTO>> getPendingClubs(Pageable pageable) {
        Page<ClubRespDTO> pendingClubs = clubServiceInterface.getClubsByStatusPending(pageable);
        return new ResponseEntity<>(pendingClubs, HttpStatus.OK);
    }
    @PutMapping("/accept/{id}")
    public ResponseEntity<ClubRespDTO> acceptRequest(@PathVariable Integer id) {
        ClubRespDTO clubResp = clubServiceInterface.acceptRequest(id);
        return ResponseEntity.ok().body(clubResp);
    }

    @PutMapping("/refuse/{id}")
    public ResponseEntity<ClubRespDTO> refuseRequest(@PathVariable Integer id) {
        ClubRespDTO clubResp = clubServiceInterface.refuseRequest(id);
        return ResponseEntity.ok().body(clubResp);
    }

    @GetMapping("/owner/{ownerId}/accepted")
    public ClubRespDTO getAcceptedClubByOwner(@PathVariable Integer ownerId) {
        return clubServiceInterface.getAcceptedClubByOwner(ownerId);
    }
}
