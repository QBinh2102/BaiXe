/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Booking;
import com.bxtm.services.BookingService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author toquocbinh2102
 */
@Controller
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    
    @RequestMapping("/")
    public String getAllBookings(Model model, @RequestParam Map<String,String> params){
        model.addAttribute("bookings", this.bookingService.getBookings(params));
        return "bookings";
    }
    
    @PostMapping("/update/")
    public String update(@RequestParam Map<String,String> params){
        Booking booking = this.bookingService.getBookingById(Integer.parseInt(params.get("idBooking")));
        booking.setTrangThai(params.get("trangThai"));
        this.bookingService.createOrUpdate(booking);
        return "redirect:/bookings/";
    }
    
    @GetMapping("/{idBooking}")
    public String updateView(Model model, @PathVariable(value="idBooking") int id){
        model.addAttribute("booking", this.bookingService.getBookingById(id));
        return "booking";
    }
}
