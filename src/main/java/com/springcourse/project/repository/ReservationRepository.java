package com.springcourse.project.repository;

import com.springcourse.project.model.Reservation;
import com.springcourse.project.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {

    List<Reservation> findReservationsByStatusEquals(ReservationStatus status);

    @Modifying
    @Query("UPDATE Reservation r SET r.status = ?1 WHERE r.reservationNumber = ?2")
    void updateCarStatusByBarcode(ReservationStatus status, String reservationNumber);
}
