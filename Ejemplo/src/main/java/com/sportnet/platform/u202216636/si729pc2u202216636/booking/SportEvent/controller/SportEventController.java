package com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.controller;

import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.request.SportEventRequestDTO;
import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.dto.response.SportEventResponseDTO;
import com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.service.SportEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Sport Events Controller Class
 * Provides methods to obtain and create new sport events
 * @author Diego Castro - U202216636
 * @version 1.0, 2023-11-08
 */
@Tag(name="Sport Event Controller", description = "Controller to register new sport events")
@RestController
@RequestMapping("/api/v1")
public class SportEventController {
    /**
     * The Sport event service.
     */
    @Autowired
    SportEventService sportEventService;

    /**
     * Method to create a new sport event.
     *
     * @param sportEventRequestDTO the sport event request DTO
     * @return the response entity that contains the created sport event and HTTP Status
     */
    @Operation(summary = "Create a new sport event")
    @ApiResponse(responseCode = "201", description = "Successful operation",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = SportEventResponseDTO.class)))
    @Transactional
    @PostMapping("/sport-events")
    public ResponseEntity<SportEventResponseDTO> createSportEvent(@RequestBody SportEventRequestDTO sportEventRequestDTO){
        return new ResponseEntity<>(sportEventService.createSportEvent(sportEventRequestDTO), HttpStatus.CREATED);
    }

    /**
     * Method to obtain all existing sport events.
     *
     * @return the response entity that contains the sport events list and HTTP Status
     */
    @Operation(summary = "Obtain a list of all sport events")
    @ApiResponse(responseCode = "200", description = "Successful operation",
    content = @Content(mediaType = "application/json",
    schema = @Schema(implementation = SportEventResponseDTO.class)))
    @Transactional(readOnly = true)
    @GetMapping("/sport-events")
    public ResponseEntity<List<SportEventResponseDTO>> getAllSportEvents(){
        return new ResponseEntity<>(sportEventService.getAllSportEvents(), HttpStatus.OK);
    }
}
