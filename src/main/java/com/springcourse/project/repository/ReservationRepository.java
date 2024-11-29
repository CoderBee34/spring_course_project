package com.springcourse.project.repository;

import com.springcourse.project.model.Reservation;
import com.springcourse.project.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {

    List<Reservation> findReservationsByStatusEqualsOrStatusEquals(ReservationStatus status1, ReservationStatus status2);
}
