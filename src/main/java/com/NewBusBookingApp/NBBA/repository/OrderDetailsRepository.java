package com.NewBusBookingApp.NBBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.NewBusBookingApp.NBBA.model.tktdetails.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long>{

}
