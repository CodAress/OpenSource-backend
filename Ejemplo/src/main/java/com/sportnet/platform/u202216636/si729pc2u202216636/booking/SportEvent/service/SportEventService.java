package com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.service;

import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.request.SportEventRequestDTO;
import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.response.SportEventResponseDTO;

import java.util.List;

/**
 * Sport Events Service Interface
 * Provides methods to obtain and create new sport events in the database
 * @author Diego Castro - U202216636
 * @version 1.0, 2023-11-08
 */
public interface SportEventService {
    List<SportEventResponseDTO> getAllSportEvents();
    SportEventResponseDTO createSportEvent(SportEventRequestDTO sportEventRequest);
}
