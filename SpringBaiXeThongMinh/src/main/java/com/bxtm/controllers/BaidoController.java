/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Baido;
import com.bxtm.services.BaidoService;
import java.util.List;
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
@RequestMapping("/baidos")
public class BaidoController {
    @Autowired
    private BaidoService baiDoService;
    
   
    
    @RequestMapping("/")
    public String baido(Model model, @RequestParam Map<String,String> params){
        List<Baido> baidos = this.baiDoService.getBaiDo(params);
        model.addAttribute("baidos", baidos);
        
        return "baidos";
    }
    
    @GetMapping("/baido")
    public String createView(Model model) {
        model.addAttribute("baido", new Baido());
        return "baido";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute(value = "baido") Baido bd) {
        this.baiDoService.createOrUpdate(bd);
        return "redirect:/baidos/";
    } 
    
    @GetMapping("/{idBaiDo}")
    public String updateView(Model model, @PathVariable(value = "idBaiDo") int id) {
        model.addAttribute("baido", this.baiDoService.getBaiDoById(id));
        return "baido";
    }
    
   

}
