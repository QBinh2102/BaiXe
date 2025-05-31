/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Baido;
import com.bxtm.pojo.QuanHuyenDTO;
import com.bxtm.services.BaidoService;
import java.util.Arrays;
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
        List<QuanHuyenDTO> dsQuanHuyen = Arrays.asList(
                new QuanHuyenDTO("Quận 1", "Quận 1"),
                new QuanHuyenDTO("Quận 2", "Quận 2"),
                new QuanHuyenDTO("Quận 3", "Quận 3"),
                new QuanHuyenDTO("Quận 4", "Quận 4"),
                new QuanHuyenDTO("Quận 5", "Quận 5"),
                new QuanHuyenDTO("Quận 6", "Quận 6"),
                new QuanHuyenDTO("Quận 7", "Quận 7"),
                new QuanHuyenDTO("Quận 8", "Quận 8"),
                new QuanHuyenDTO("Quận 9", "Quận 9"),
                new QuanHuyenDTO("Quận 10", "Quận 10"),
                new QuanHuyenDTO("Quận 11", "Quận 11"),
                new QuanHuyenDTO("Quận 12", "Quận 12"),
                new QuanHuyenDTO("Bình Thạnh", "Bình Thạnh"),
                new QuanHuyenDTO("Bình Tân", "Bình Tân"),
                new QuanHuyenDTO("Phú Nhuận", "Phú Nhuận"),
                new QuanHuyenDTO("Tân Phú", "Tân Phú"),
                new QuanHuyenDTO("Gò Vấp", "Gò Vấp"),
                new QuanHuyenDTO("Thủ Đức", "Thủ Đức"),
                new QuanHuyenDTO("Hóc Môn", "Hóc Môn"),
                new QuanHuyenDTO("Bình Chánh", "Bình Chánh"),
                new QuanHuyenDTO("Nhà Bè", "Nhà Bè"),
                new QuanHuyenDTO("Cần Giờ", "Cần Giờ")
        );
        model.addAttribute("dsQuanHuyen", dsQuanHuyen);
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
