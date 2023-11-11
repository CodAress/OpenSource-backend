package com.sportnet.platform.u202216636.si729pc2u202216636.booking.SportEvent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="sport_event")
public class SportEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="event_name")
    String eventName;

    @Column(name="sport_type")
    String sportType;

    @Column(name="organizer_id")
    Long organizerId;

    @Column(name="location")
    String location;

    @Column(name="capacity")
    int capacity;

    @Column(name="event_date")
    LocalDate eventDate;
}
