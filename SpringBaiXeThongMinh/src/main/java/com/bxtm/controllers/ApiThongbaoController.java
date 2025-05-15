/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Nguoidung;
import com.bxtm.pojo.Thongbao;
import com.bxtm.services.NguoidungService;
import com.bxtm.services.ThongbaoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
public class ApiThongbaoController {
    @Autowired
    private ThongbaoService thongBaoService;
    @Autowired
    private NguoidungService nguoiDungService;
    
    @GetMapping("/thongbaos")
    public ResponseEntity<List<Thongbao>> getThongBaos(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.thongBaoService.getThongBao(params), HttpStatus.OK);
    }
    
    @GetMapping("/thongbaos/{idThongBao}")
    public ResponseEntity<Thongbao> getThongBaoById(@PathVariable(value="idThongBao") int id){
        return new ResponseEntity<>(this.thongBaoService.getThongBaoById(id), HttpStatus.OK);
    }
    
    @PostMapping("/thongbaos")
    public ResponseEntity<Thongbao> add(@RequestParam Map<String,String> params){
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungById(Integer.parseInt(params.get("idNguoiDung")));
        Thongbao newThongBao = new Thongbao();
        newThongBao.setIdNguoiDung(nguoiDung);
        newThongBao.setNoiDung(params.get("noiDung"));
        return new ResponseEntity<>(this.thongBaoService.createOrUpdate(newThongBao), HttpStatus.CREATED);
    }
}
