package com.achchaimae.loisiresa;

import com.achchaimae.loisiresa.Domain.activity.Activity;
import com.achchaimae.loisiresa.Domain.activity.ActivityRepository;
import com.achchaimae.loisiresa.Domain.activity.ActivityService;
import com.achchaimae.loisiresa.Domain.activity.Tag;
import com.achchaimae.loisiresa.Domain.activity.dto.ActivityReqDTO;
import com.achchaimae.loisiresa.Domain.activity.dto.ActivityRespDTO;
import com.achchaimae.loisiresa.Domain.club.Club;
import com.achchaimae.loisiresa.Exception.RecordAlreadyExistsException;
import com.achchaimae.loisiresa.Exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActivityServiceTest {

    @Mock
    private ActivityRepository activityRepository;

    private ActivityService activityService;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
        activityService = new ActivityService(modelMapper, activityRepository);
    }

    @Test
    void testGetActivities() {
        Activity activity1 = new Activity();
        Activity activity2 = new Activity();
        List<Activity> activities = Arrays.asList(activity1, activity2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Activity> activityPage = new PageImpl<>(activities, pageable, activities.size());

        when(activityRepository.findAll(pageable)).thenReturn(activityPage);

        Page<ActivityRespDTO> result = activityService.getActivities(pageable);

        assertEquals(2, result.getContent().size());
        verify(activityRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindActivity() {
        int activityId = 1;
        Activity activity = new Activity();
        activity.setId(activityId);

        when(activityRepository.findById(activityId)).thenReturn(Optional.of(activity));

        ActivityRespDTO result = activityService.findActivity(activityId);

        assertNotNull(result);
        verify(activityRepository, times(1)).findById(activityId);
    }

    @Test
    void testSaveActivity() {
        ActivityReqDTO activityReqDTO = new ActivityReqDTO();
        activityReqDTO.setId(1);
        activityReqDTO.setClub_id(1);

        Activity activity = modelMapper.map(activityReqDTO, Activity.class);
        Club club = new Club();
        club.setId(1);
        activity.setClub(club);

        when(activityRepository.existsById(activityReqDTO.getId())).thenReturn(false);
        when(activityRepository.save(any(Activity.class))).thenReturn(activity);

        ActivityRespDTO result = activityService.saveActivity(activityReqDTO);

        assertNotNull(result);
        assertEquals(activityReqDTO.getId(), result.getId());
        verify(activityRepository, times(1)).existsById(activityReqDTO.getId());
        verify(activityRepository, times(1)).save(any(Activity.class));
    }



    @Test
    void testDeleteActivity() {
        int activityId = 1;
        Activity activity = new Activity();
        activity.setId(activityId);

        when(activityRepository.findById(activityId)).thenReturn(Optional.of(activity));

        Integer result = activityService.DeleteActivity(activityId);

        assertEquals(1, result);
        verify(activityRepository, times(1)).findById(activityId);
        verify(activityRepository, times(1)).deleteById(activityId);
    }

    @Test
    void testGetActivitiesByClubId() {
        int clubId = 1;
        Activity activity1 = new Activity();
        Activity activity2 = new Activity();
        List<Activity> activities = Arrays.asList(activity1, activity2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Activity> activityPage = new PageImpl<>(activities, pageable, activities.size());

        when(activityRepository.findByClubId(clubId, pageable)).thenReturn(activityPage);

        Page<ActivityRespDTO> result = activityService.getActivitiesByClubId(clubId, pageable);

        assertEquals(2, result.getContent().size());
        verify(activityRepository, times(1)).findByClubId(clubId, pageable);
    }

    @Test
    void testGetActivitiesByTag() {
        Tag tag = Tag.art;
        Activity activity1 = new Activity();
        Activity activity2 = new Activity();
        List<Activity> activities = Arrays.asList(activity1, activity2);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Activity> activityPage = new PageImpl<>(activities, pageable, activities.size());

        when(activityRepository.getActivitiesByTag(tag, pageable)).thenReturn(activityPage);

        Page<ActivityRespDTO> result = activityService.getActivitiesByTag(tag, pageable);

        assertEquals(2, result.getContent().size());
        verify(activityRepository, times(1)).getActivitiesByTag(tag, pageable);
    }

    @Test
    void testLike() {
        int activityId = 1;
        Activity activity = new Activity();
        activity.setId(activityId);
        activity.setRating(5);

        when(activityRepository.findById(activityId)).thenReturn(Optional.of(activity));
        when(activityRepository.save(any(Activity.class))).thenReturn(activity);

        ActivityRespDTO result = activityService.Like(activityId);

        assertNotNull(result);
        assertEquals(6, result.getRating());
        verify(activityRepository, times(1)).findById(activityId);
        verify(activityRepository, times(1)).save(any(Activity.class));
    }

    @Test
    void testSaveActivity_ThrowsRecordAlreadyExistsException() {
        ActivityReqDTO activityReqDTO = new ActivityReqDTO();
        activityReqDTO.setId(1);

        when(activityRepository.existsById(activityReqDTO.getId())).thenReturn(true);

        assertThrows(RecordAlreadyExistsException.class, () -> activityService.saveActivity(activityReqDTO));
        verify(activityRepository, times(1)).existsById(activityReqDTO.getId());
        verify(activityRepository, never()).save(any(Activity.class));
    }

    @Test
    void testUpdateActivity_ThrowsRecordAlreadyExistsException() {
        int activityId = 1;
        ActivityReqDTO activityReqDTO = new ActivityReqDTO();
        activityReqDTO.setId(2);

        Activity existingActivity = new Activity();
        existingActivity.setId(activityId);

        when(activityRepository.findById(activityId)).thenReturn(Optional.of(existingActivity));
        when(activityRepository.existsById(activityReqDTO.getId())).thenReturn(true);

        assertThrows(RecordAlreadyExistsException.class, () -> activityService.updateActivity(activityReqDTO, activityId));
        verify(activityRepository, times(1)).findById(activityId);
        verify(activityRepository, times(1)).existsById(activityReqDTO.getId());
        verify(activityRepository, never()).save(any(Activity.class));
    }


    @Test
    void testLike_ThrowsResourceNotFoundException() {
        int activityId = 1;

        when(activityRepository.findById(activityId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> activityService.Like(activityId));
        verify(activityRepository, times(1)).findById(activityId);
        verify(activityRepository, never()).save(any(Activity.class));
    }
}