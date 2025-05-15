/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Baido;
import com.bxtm.pojo.Baotri;
import com.bxtm.services.BaidoService;
import com.bxtm.services.BaotriService;
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
public class ApiBaotriController {
    @Autowired
    private BaotriService baoTriService;
    @Autowired
    private BaidoService baiDoService;
    
    @GetMapping("/baotris")
    public ResponseEntity<List<Baotri>> getBaoTris(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.baoTriService.getBaoTri(params), HttpStatus.OK);
    }
    
    @GetMapping("/baotris/{idBaoTri}")
    public ResponseEntity<Baotri> getBaoTriById(@PathVariable(value="idBaoTri") int id){
        return new ResponseEntity<>(this.baoTriService.getBaoTriById(id), HttpStatus.OK);
    }
    
    @PostMapping("/baotris")
    public ResponseEntity<Baotri> add(@RequestParam Map<String,String> params){
        Baido baiDo = this.baiDoService.getBaiDoById(Integer.parseInt(params.get("idBaiDo")));
        Baotri newBaoTri = new Baotri();
        newBaoTri.setIdBaiDo(baiDo);
        newBaoTri.setTinhTrang(params.get("tinhTrang"));
        return new ResponseEntity<>(this.baoTriService.createOrUpdate(newBaoTri), HttpStatus.CREATED);
    }
}
