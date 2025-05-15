/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Booking;
import com.bxtm.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author toquocbinh2102
 */
@Controller
@RequestMapping("/baidos/{idBaiDo}/choDo/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    
    @GetMapping("/")
    public String createView(Model model){
        model.addAttribute("booking", new Booking());
        return "booking";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute(value="booking") Booking booking){
        this.bookingService.createOrUpdate(booking);
        return "redirect:/baidos/{idBaiDo}/choDo/";
    }
    
    @GetMapping("/{idBooking}")
    public String updateView(Model model, @PathVariable(value="idBooking") int id){
        model.addAttribute("booking", this.bookingService.getBookingById(id));
        return "booking";
    }
}
