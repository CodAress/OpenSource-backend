package com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.request;

import lombok.Data;

import java.time.LocalDate;

/**
 * Sport Events Request DTO Class
 * The request DTO for the sport events controller class
 * @author Diego Castro - U202216636
 * @version 1.0, 2023-11-08
 */
@Data
public class SportEventRequestDTO {
    String eventName;
    String sportType;
    Long organizerId;
    String location;
    int capacity;
    LocalDate eventDate;
}
