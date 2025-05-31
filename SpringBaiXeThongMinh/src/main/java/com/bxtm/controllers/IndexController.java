/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author toquocbinh2102
 */
@Controller
@ControllerAdvice
public class IndexController {
    
    @ModelAttribute
    public void commonResponse(Model model){
        model.addAttribute("trangChu","Trang chủ");
        model.addAttribute("baiDo","Bãi đỗ");
        model.addAttribute("nguoiDung","Người dùng");
        model.addAttribute("booking","Booking");
    } 
    
    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }
}
