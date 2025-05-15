/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Thongbao;
import com.bxtm.services.ThongbaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author toquocbinh2102
 */
@Controller
@RequestMapping("/thongbaos")
public class ThongbaoController {
    @Autowired
    private ThongbaoService thongBaoService;
    
    @RequestMapping("/admin/")
    public String getThongBaos(Model model){
        model.addAttribute("thongbaos", this.thongBaoService.getThongBao(null));
        return "thongbaos";
    }
    
    @GetMapping("/admin/")
    public String createView(Model model){
        model.addAttribute("newThongBao", new Thongbao());
        return "thongbaos";
    }
    
    @PostMapping("/admin/add")
    public String add(@ModelAttribute(value="newThongBao") Thongbao thongbao){
        this.thongBaoService.createOrUpdate(thongbao);
        return "redirect:/thongbaos/admin/";
    }
}
