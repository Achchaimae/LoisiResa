package com.achchaimae.loisiresa.Domain.activity;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityRespDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository  extends JpaRepository<Activity,Integer> {
    Page<Activity> findByClubId(Integer clubId, Pageable pageable);
    Page<Activity> getActivitiesByTag(Tag tag, Pageable pageable);
}
