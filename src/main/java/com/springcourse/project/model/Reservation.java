package com.springcourse.project.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @Column(unique = true, length = 8)
    private String reservationNumber;

    @Column()
    private LocalDateTime creationDate;
    @Column()
    private LocalDateTime pickUpDateTime;
    @Column()
    private LocalDateTime dropOffDateTime;
    @Column()
    @ManyToOne
    private Location pickUpLocation;
    @Column()
    @ManyToOne
    private Location dropOffLocation;
    @Column()
    private LocalDateTime returnDate;
    @Column()
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}
