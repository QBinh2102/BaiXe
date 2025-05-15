/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Danhgia;
import com.bxtm.services.DanhgiaService;
import java.util.HashMap;
import java.util.Map;
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
@RequestMapping("/baidos/{idBaiDo}/danhgias")
public class DanhgiaController {
    @Autowired
    private DanhgiaService danhGiaService;
    
    @RequestMapping("/")
    public String getDanhGiaByBaiDo(Model model, @PathVariable(value="idBaiDo") int id){
        Map<String,String> params = new HashMap<>();
        params.put("idBaiDo", String.valueOf(id));
        model.addAttribute("danhgias", this.danhGiaService.getDanhGia(params));
        return "danhgias";
    }
    
    @GetMapping("/")
    public String createView(Model model){
        model.addAttribute("newDanhGia", new Danhgia());
        return "danhgias";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute(value = "newDanhGia") Danhgia danhGia) {
        this.danhGiaService.createOrUpdate(danhGia);
        return "redirect:/baidos/{idBaiDo}/danhgias/";
    } 
}
