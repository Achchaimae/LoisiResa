package com.achchaimae.loisiresa.Domain.activity;

import com.achchaimae.loisiresa.Domain.activity.dto.ActivityReqDTO;
import com.achchaimae.loisiresa.Domain.activity.dto.ActivityRespDTO;
import com.achchaimae.loisiresa.Exception.RecordAlreadyExistsException;
import com.achchaimae.loisiresa.Exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService implements ActivityServiceInterface{

    ActivityRepository activityRepository;
    private final ModelMapper modelMapper;

    public ActivityService(ModelMapper modelMapper, ActivityRepository activityRepository) {
        this.activityRepository= activityRepository;
        this.modelMapper = modelMapper;
    }



    public Page<ActivityRespDTO> getActivities(Pageable pageable){
      Page<Activity> entityPage = activityRepository.findAll(pageable);
      return  entityPage.map(entity -> modelMapper.map(entity, ActivityRespDTO.class));
    }

    public ActivityRespDTO findActivity(Integer activityId){
        Optional<Activity> activityOptional= activityRepository.findById(activityId);
        if(activityOptional.isPresent()){
            Activity activity= activityOptional.get();
            return  modelMapper.map(activity, ActivityRespDTO.class);
        }
        return null;
    }

    public ActivityRespDTO saveActivity(ActivityReqDTO activity){
        if(activityRepository.existsById(activity.getId())){
            throw new RecordAlreadyExistsException("Activity with Id "+activity.getId()+ "already exists.");
        }
        return modelMapper.map(activityRepository.save(modelMapper.map(activity, Activity.class)), ActivityRespDTO.class);
    }

    public ActivityRespDTO updateActivity(ActivityReqDTO activity, Integer id){
        Optional<Activity> activityOptional= activityRepository.findById(id);
        if(activityOptional.isPresent()){
            Activity existActivity = activityOptional.get();
            if(existActivity.getId()!=activity.getId() && activityRepository.existsById(activity.getId())){
                throw new RecordAlreadyExistsException("Member with num " + activity.getId() + " already exists.");
            }
            activity.setId(Integer.valueOf(existActivity.getId()));
            return  modelMapper.map(activityRepository.save(modelMapper.map(activity,Activity.class)), ActivityRespDTO.class);
        }else {
        throw new ResourceNotFoundException("Activity with id " + id + " not found.");
        }
    }

    public Integer DeleteActivity(Integer activityId){
        Optional<Activity> exist = activityRepository.findById(activityId);
        if(exist.isPresent()){
            activityRepository.deleteById(activityId);
            return 1;
        }
        return 0;
    }

}
