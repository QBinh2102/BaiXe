/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Booking;
import com.bxtm.services.BookingService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
public class ApiBookingController {
    @Autowired
    private BookingService bookingService;
    
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.bookingService.getBookings(params), HttpStatus.OK);
    }
    
    @GetMapping("/bookings/{idBooking}")
    public ResponseEntity<Booking> getBookingById(@PathVariable(value="idBooking")int id){
        return new ResponseEntity<>(this.bookingService.getBookingById(id), HttpStatus.OK);
    }
}
