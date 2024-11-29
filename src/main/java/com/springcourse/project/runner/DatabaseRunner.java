package com.springcourse.project.runner;

import com.springcourse.project.model.*;
import com.springcourse.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DatabaseRunner implements CommandLineRunner {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Override
    public void run(String... args) throws Exception {

        Location location1 = new Location();
        location1.setCode(1);
        location1.setName("Downtown");
        location1.setAddress("123 Main St, City");

        Location location2 = new Location();
        location2.setCode(2);
        location2.setName("Airport");
        location2.setAddress("456 Airport Rd, City");

        locationRepository.saveAll(Arrays.asList(location1, location2));


        ServiceModel service1 = new ServiceModel();
        service1.setId(1);
        service1.setName("GPS");
        service1.setPrice(5.0);

        ServiceModel service2 = new ServiceModel();
        service2.setId(2);
        service2.setName("Child Seat");
        service2.setPrice(10.0);

        serviceRepository.saveAll(Arrays.asList(service1, service2));

        Equipment equipment1 = new Equipment();
        equipment1.setId(1);
        equipment1.setName("Snow Chains");
        equipment1.setPrice(30.0);

        Equipment equipment2 = new Equipment();
        equipment2.setId(2);
        equipment2.setName("Car Seat");
        equipment2.setPrice(25.0);

        equipmentRepository.saveAll(Arrays.asList(equipment1, equipment2));

        Car car1 = new Car("12345", "AB123CD", 4, "Toyota", "Corolla", 15000.5, "Automatic", 50.0, CarType.STANDARD, CarStatus.AVAILABLE);
        Car car2 = new Car("67890", "EF456GH", 7, "Ford", "Explorer", 12000.3, "Manual", 70.0, CarType.SUV, CarStatus.RESERVED);
        carRepository.saveAll(Arrays.asList(car1, car2));

        Member member = new Member();
        member.setName("John Doe");
        member.setAddress("789 Elm St, City");
        member.setEmail("john.doe@example.com");
        member.setPhone("123-456-7890");
        member.setDrivingLicenseId("DL1234567");
        memberRepository.save(member);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber("7891234");
        reservation.setCar(car1);
        reservation.setCreationDate(LocalDate.now());
        reservation.setPickUpDateTime(LocalDate.now().plusDays(5));
        reservation.setDropOffDateTime(LocalDate.now().plusDays(15));
        reservation.setPickUpLocation(location1);
        reservation.setDropOffLocation(location2);
        reservation.setReturnDate(LocalDate.now().plusDays(20));
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setEquipmentList(Arrays.asList(equipment1));
        reservation.setServiceList(Arrays.asList(service2));
        reservation.setMember(member);
        member.getReservationList().add(reservation);
        reservationRepository.save(reservation);

        memberRepository.save(member);
    }
}
