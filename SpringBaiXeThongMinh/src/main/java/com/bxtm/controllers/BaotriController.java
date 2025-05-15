/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Baotri;
import com.bxtm.services.BaotriService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author huynh
 */
@Controller
@RequestMapping("/baotris")
public class BaotriController {
    @Autowired
    private BaotriService baoTriService;
    
    @RequestMapping("/admin/")
    public String baoTri(Model model, Map<String,String> params){
        model.addAttribute("baotris",this.baoTriService.getBaoTri(params));
        return "baotris";
    }
    
    @GetMapping("/admin/")
    public String createView(Model model){
        model.addAttribute("baotri", new Baotri());
        return "baotris";
    }
    
    @PostMapping("/admin/add")
    public String add(@ModelAttribute(value="baotri") Baotri baoTri){
        this.baoTriService.createOrUpdate(baoTri);
        return "redirect:/baotris/admin/";
    }
}