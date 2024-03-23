package com.achchaimae.loisiresa.Domain.activity;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityReqDTO;
import com.achchaimae.loisiresa.Domain.activity.dto.ActivityRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityServiceInterface{
    Page<ActivityRespDTO> getActivities(Pageable pageable);
    ActivityRespDTO findActivity(Integer activityId);
    ActivityRespDTO saveActivity(ActivityReqDTO activity);
    ActivityRespDTO updateActivity(ActivityReqDTO activity, Integer id);
    Integer DeleteActivity(Integer activityId);

    Page<ActivityRespDTO> getActivitiesByClubId(Integer clubId, Pageable pageable);
    Page<ActivityRespDTO> getActivitiesByTag(Tag tag, Pageable pageable);
}
