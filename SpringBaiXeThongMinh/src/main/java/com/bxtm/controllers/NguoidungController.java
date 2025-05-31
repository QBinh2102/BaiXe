/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Nguoidung;
import com.bxtm.services.NguoidungService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/nguoidungs")
public class NguoidungController {
    @Autowired
    private NguoidungService nguoiDungService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @RequestMapping("/")
    public String getAllNguoiDung(Model model, @RequestParam Map<String,String> params){
        model.addAttribute("nguoidungs", this.nguoiDungService.getNguoiDung(params));
        return "nguoidungs";
    }
    
    @GetMapping("/nguoidung")
    public String createView(Model model) {
        model.addAttribute("nguoidung", new Nguoidung());
        return "nguoidung";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute(value = "nguoidung") Nguoidung nguoiDung) {
        if(nguoiDung.getId()!=null){
            Nguoidung nd = this.nguoiDungService.getNguoiDungById(nguoiDung.getId());
            if(nguoiDung.getMatKhau()==null || nguoiDung.getMatKhau().isBlank()){
                nguoiDung.setMatKhau(nd.getMatKhau());
            }else{
                nguoiDung.setMatKhau(passwordEncoder.encode(nguoiDung.getMatKhau()));
            }
            if(nguoiDung.getFile() == null || nguoiDung.getFile().isEmpty()){
                nguoiDung.setAvatar(nd.getAvatar());
            }
            if(nguoiDung.getFileAnhXe() == null || nguoiDung.getFileAnhXe().isEmpty()){
                nguoiDung.setAnhXe(nd.getAnhXe());
            }
        }
        this.nguoiDungService.createOrUpdate(nguoiDung);
        
        return "redirect:/nguoidungs/";
    } 
    
    @GetMapping("/{idNguoiDung}")
    public String updateView(Model model, @PathVariable(value = "idNguoiDung") int id) {
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungById(id);
        nguoiDung.setMatKhau("");
        model.addAttribute("nguoidung", nguoiDung);
        return "nguoidung";
    }
}
