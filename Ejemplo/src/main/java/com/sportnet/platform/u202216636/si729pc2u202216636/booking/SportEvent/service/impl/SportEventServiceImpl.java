package com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.service.impl;

import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.request.SportEventRequestDTO;
import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.response.SportEventResponseDTO;
import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.model.SportEvent;
import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.repository.SportEventRepository;
import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.service.SportEventService;
import com.sportnet.platform.u202216636.si729pc2u202216636.shared.exception.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sport Events Service Implementation Class
 * Provides methods to obtain, create and validate new sport events in the database
 * @author Diego Castro - U202216636
 * @version 1.0, 2023-11-08
 */
@Service
public class SportEventServiceImpl implements SportEventService {
    @Autowired
    private SportEventRepository sportEventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SportEventResponseDTO> getAllSportEvents() {
        List<SportEvent> sportEvents = sportEventRepository.findAll();
        return sportEvents.stream()
                .map(sportEvent -> modelMapper.map(sportEvent, SportEventResponseDTO.class))
                .toList();
    }

    @Override
    public SportEventResponseDTO createSportEvent(SportEventRequestDTO sportEventRequest) {
        validateSportEvent(sportEventRequest);
        SportEvent sportEvent = modelMapper.map(sportEventRequest, SportEvent.class);
        sportEventRepository.save(sportEvent);
        return modelMapper.map(sportEvent, SportEventResponseDTO.class);
    }

    private void validateSportEvent(SportEventRequestDTO sportEventRequest){
        boolean existsByEventNameAndSportTypeAndLocation = sportEventRepository.existsByEventNameAndSportTypeAndLocation(
                sportEventRequest.getEventName(),
                sportEventRequest.getSportType(),
                sportEventRequest.getLocation()
        );
        if(existsByEventNameAndSportTypeAndLocation){
            throw new ValidationException("Sport event already exists with the same name, type and location");
        }

        boolean existsByOrganizerIdAndEventDate = sportEventRepository.existsByOrganizerIdAndEventDate
                (sportEventRequest.getOrganizerId(), sportEventRequest.getEventDate());
        if(existsByOrganizerIdAndEventDate){
            throw new ValidationException("Sport event cannot be created because the organizer already has an event on the same date");
        }

        if(sportEventRequest.getEventName().isEmpty()){
            throw new ValidationException("Sport event name cannot be empty");
        }

        if(sportEventRequest.getSportType().isEmpty()){
            throw new ValidationException("Sport event type cannot be empty");
        }

        if(sportEventRequest.getOrganizerId() == null){
            throw new ValidationException("Sport event organizer ID cannot be empty");
        }

        if(sportEventRequest.getLocation().isEmpty()){
            throw new ValidationException("Sport event location cannot be empty");
        }

        if(sportEventRequest.getCapacity() <= 0){
            throw new ValidationException("Sport event capacity must be greater than 0");
        }

        if(sportEventRequest.getEventDate() == null){
            throw new ValidationException("Sport event date cannot be empty");
        }
    }
}
