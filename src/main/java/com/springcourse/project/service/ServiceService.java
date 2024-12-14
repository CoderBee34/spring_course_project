package com.springcourse.project.service;

import com.springcourse.project.dto.AdditionalServiceRequestDTO;
import com.springcourse.project.model.Member;
import com.springcourse.project.model.Reservation;
import com.springcourse.project.model.ServiceModel;
import com.springcourse.project.repository.MemberRepository;
import com.springcourse.project.repository.ReservationRepository;
import com.springcourse.project.repository.ServiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public boolean addServiceToReservation(AdditionalServiceRequestDTO additionalServiceRequestDTO){
        String reservationNumber = additionalServiceRequestDTO.getReservationNumber();
        int serviceCode = additionalServiceRequestDTO.getServiceId();
        Optional<ServiceModel> serviceOptional = serviceRepository.findById(serviceCode);
        if (serviceOptional.isPresent()){
            ServiceModel service = serviceOptional.get();
            Optional<Reservation> reservationOptional = reservationRepository.findById(reservationNumber);
            if (reservationOptional.isPresent()){

                Reservation reservation = reservationOptional.get();
                List<ServiceModel> reservationServiceList = reservation.getServiceList();

                for (ServiceModel serviceModel : reservationServiceList){
                    if (serviceModel.getId() == service.getId()){
                        return false;
                    }
                }
                reservationServiceList.add(service);
                reservationRepository.save(reservation);
                return true;
            }
            return false;
        }
        return false;
    }
    public ServiceModel createService(int id, String name, double price){
        ServiceModel service = new ServiceModel();
        service.setId(id);
        service.setName(name);
        service.setPrice(price);
        serviceRepository.save(service);
        return service;
    }
    public void saveReservationForTest(Reservation reservation){
        reservationRepository.save(reservation);
    }
    public Optional<Reservation> findReservationByIdForTest(String id){
        return reservationRepository.findById(id);
    }
    public void saveMemberForTest(Member member){
        memberRepository.save(member);
    }
}
