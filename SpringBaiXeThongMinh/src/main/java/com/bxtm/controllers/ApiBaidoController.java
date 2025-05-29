/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Baido;
import com.bxtm.services.BaidoService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiBaidoController {

    @Autowired
    private BaidoService baiDoService;

    @GetMapping("/baidos/")
    public ResponseEntity<List<Baido>> getBaiDos(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.baiDoService.getBaiDo(params), HttpStatus.OK);
    }

    @GetMapping("/baidos/{idBaiDo}/")
    public ResponseEntity<Baido> getBaiDoById(@PathVariable(value = "idBaiDo") int id) {
        return new ResponseEntity<>(this.baiDoService.getBaiDoById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/secure/admin/baidos/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Baido> addBaiDo(@RequestParam Map<String, String> params,
            @RequestParam("anhBai") MultipartFile anhBai) {
        System.out.println("Params received: " + params);
        System.out.println("File received: " + (anhBai != null ? anhBai.getOriginalFilename() : "NULL"));
        Baido baiDo = new Baido();
        baiDo.setTen(params.get("ten"));
        baiDo.setDiaChi(params.get("diaChi"));
        baiDo.setGiaTien(new BigDecimal(params.get("giaTien")));
        baiDo.setSoLuong(Integer.parseInt(params.get("soLuong")));
        baiDo.setTienIch(params.get("tienIch"));
//        baiDo.setTrangThai(params.get("trangThai"));
        baiDo.setFile(anhBai);

        if (params.get("ten") == null || params.get("diaChi") == null || params.get("giaTien") == null || params.get("soLuong") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Thiếu tham số bắt buộc");
        }

        return new ResponseEntity<>(this.baiDoService.createOrUpdate(baiDo), HttpStatus.CREATED);
    }

    @PatchMapping(path = "/secure/admin/baidos/{id}/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateBaiDo(
            @PathVariable("id") int id,
            @RequestParam Map<String, String> params,
            @RequestParam(value = "anhBai", required = false) MultipartFile anhBai) {
        try {
            Baido baiDo = this.baiDoService.getBaiDoById(id);

            // Kiểm tra null tránh lỗi
            String ten = params.get("ten");
            String diaChi = params.get("diaChi");
            String soLuongStr = params.get("soLuong");
            String giaTienStr = params.get("giaTien");
            String tienIch = params.get("tienIch");
            String trangThai = params.get("trangThai");

            if(ten!=null&&!ten.isEmpty()){
                baiDo.setTen(ten);
            }
            if(diaChi!=null&&!diaChi.isEmpty()){
                baiDo.setDiaChi(diaChi);
            }
            if(soLuongStr!=null&&!soLuongStr.isEmpty()){
                baiDo.setSoLuong(Integer.parseInt(soLuongStr));
            }
            if(giaTienStr!=null&&!giaTienStr.isEmpty()){
                baiDo.setGiaTien(new BigDecimal(giaTienStr));
            }
            if(tienIch!=null&&!tienIch.isEmpty()){
                baiDo.setTienIch(tienIch);
            }
            if(trangThai!=null&&!trangThai.isEmpty()){
                baiDo.setTrangThai(trangThai);
            }
            if (anhBai != null && !anhBai.isEmpty()) {
                baiDo.setFile(anhBai);
            }

            Baido updated = this.baiDoService.createOrUpdate(baiDo);
            return new ResponseEntity<>(updated, HttpStatus.OK);

        } catch (NumberFormatException e) {
            return new ResponseEntity<>("Đã xảy ra lỗi khi cập nhật!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
