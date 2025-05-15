/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.services.HoadonService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author toquocbinh2102
 */
@Controller
@RequestMapping("/nguoidungs/{idNguoiDung}")
public class HoaDonController {
    @Autowired
    private HoadonService hoaDonService;
    
    @GetMapping("/hoadon")
    public String getHoaDonNguoiDung(Model model, @PathVariable(value="idNguoiDung") int id){
        Map<String,String> params = new HashMap<>();
        params.put("idNguoiDung", String.valueOf(id));
        model.addAttribute("hoadons", this.hoaDonService.getHoaDon(params));
        return "hoadon";
    }
}
