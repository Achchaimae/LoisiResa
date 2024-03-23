package com.achchaimae.loisiresa.Domain.activity;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityReqDTO;
import com.achchaimae.loisiresa.Domain.activity.dto.ActivityRespDTO;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    ActivityServiceInterface activityServiceInterface;
    ModelMapper modelMapper;
    public ActivityController(ActivityServiceInterface activityServiceInterface, ModelMapper modelMapper){
        this.activityServiceInterface = activityServiceInterface;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public ResponseEntity<Page<ActivityRespDTO>> getActivities(Pageable pageable) {

        return ResponseEntity.ok().body(activityServiceInterface.getActivities(pageable));
    }

    @PostMapping
    public ResponseEntity<ActivityRespDTO> save(@Valid @RequestBody ActivityReqDTO activity){
        ActivityRespDTO activityResp = activityServiceInterface.saveActivity(activity);
        return ResponseEntity.ok().body(activityResp);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ActivityRespDTO> update(@PathVariable Integer id, @RequestBody ActivityReqDTO activity){
        return ResponseEntity.ok().body(activityServiceInterface.updateActivity(activity, id));
    }

    @GetMapping("/find/{id}")
    public  ResponseEntity<ActivityRespDTO> getActivityById(@PathVariable Integer id){
        return ResponseEntity.ok().body(activityServiceInterface.findActivity(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActivity(@PathVariable Integer id){
        Integer deleted= activityServiceInterface.DeleteActivity(id);
        if(deleted == 1)
        {
            return ResponseEntity.ok().body("competition delete");
        }
        return ResponseEntity.badRequest().body("activity not deleted");
    }
    @GetMapping("/club/{clubId}")
    public ResponseEntity<Page<ActivityRespDTO>> getActivitiesByClubIdPageable(@PathVariable Integer clubId, Pageable pageable) {
        Page<ActivityRespDTO> activitiesPage = activityServiceInterface.getActivitiesByClubId(clubId, pageable);
        return ResponseEntity.ok(activitiesPage);
    }
    @GetMapping("/tag/{tag}")
    public ResponseEntity<Page<ActivityRespDTO>> getActivitiesByTag(@PathVariable Tag tag, Pageable pageable) {
        Page<ActivityRespDTO> activitiesPage = activityServiceInterface.getActivitiesByTag(tag, pageable);
        return ResponseEntity.ok(activitiesPage);
    }

}
