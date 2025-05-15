/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Danhgia;
import com.bxtm.services.BaidoService;
import com.bxtm.services.DanhgiaService;
import com.bxtm.services.NguoidungService;
import java.util.HashMap;
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
public class ApiDanhgiaController {
    @Autowired
    private DanhgiaService danhGiaService;
    @Autowired
    private BaidoService baiDoService;
    @Autowired
    private NguoidungService nguoiDungService;
    
    @GetMapping("/baidos/{idBaiDo}/danhgias")
    public ResponseEntity<List<Danhgia>> getDanhGiasByBaiDo(@PathVariable(value = "idBaiDo") int id){
        Map<String,String> params = new HashMap<>();
        params.put("idBaiDo", String.valueOf(id));
        return new ResponseEntity<>(this.danhGiaService.getDanhGia(params), HttpStatus.OK);
    }
    
    @PostMapping("/baidos/{idBaiDo}/danhgias")
    public ResponseEntity<Danhgia> create(@RequestParam Map<String, String> params,
            @PathVariable(value = "idBaiDo") int id) {
        Danhgia newDanhGia = new Danhgia();
        newDanhGia.setRating(Integer.parseInt(params.get("rating")));
        newDanhGia.setBinhLuan(params.get("binhLuan"));
        newDanhGia.setIdBaiDo(this.baiDoService.getBaiDoById(id));
        newDanhGia.setIdNguoiDung(this.nguoiDungService.getNguoiDungById(Integer.parseInt(params.get("idNguoiDung"))));
        
        return new ResponseEntity<>(newDanhGia, HttpStatus.CREATED);
    }
}
