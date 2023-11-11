package com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.response;

import lombok.Data;

import java.time.LocalDate;

/**
 * Sport Events Response DTO Class
 * The response DTO for the sport events controller class
 * @author Diego Castro - U202216636
 * @version 1.0, 2023-11-08
 */
@Data
public class SportEventResponseDTO {
    Long id;
    String eventName;
    String sportType;
    Long organizerId;
    String location;
    int capacity;
    LocalDate eventDate;
}
