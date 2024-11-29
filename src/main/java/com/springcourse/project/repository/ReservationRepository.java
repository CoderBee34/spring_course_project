package com.springcourse.project.repository;

import com.springcourse.project.model.Reservation;
import com.springcourse.project.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {

    List<Reservation> findReservationsByStatusEquals(ReservationStatus status);

    @Modifying
    @Query("UPDATE Reservation r SET r.status = ?1 WHERE r.reservationNumber = ?2")
    void updateReservationStatusById(ReservationStatus status, String reservationNumber);

    @Modifying
    @Query("UPDATE Reservation r SET r.returnDate = ?1 WHERE r.reservationNumber = ?2")
    void updateReservationReturnDateById(LocalDate date, String reservationNumber);

    @Modifying
    @Query("DELETE Reservation r WHERE r.reservationNumber = ?1")
    void deleteReservationById(String reservationNumber);
}
