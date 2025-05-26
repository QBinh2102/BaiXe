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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/baidos")
    public ResponseEntity<List<Baido>> getBaiDos(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.baiDoService.getBaiDo(params), HttpStatus.OK);
    }

    @GetMapping("/baidos/{idBaiDo}")
    public ResponseEntity<Baido> getBaiDoById(@PathVariable(value = "idBaiDo") int id) {
        return new ResponseEntity<>(this.baiDoService.getBaiDoById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/baidos",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Baido> addBaiDo(@RequestParam Map<String, String> params,
            @RequestParam("anhBai") MultipartFile anhBai) {
        Baido baiDo = new Baido();
        baiDo.setTen(params.get("ten"));
        baiDo.setDiaChi(params.get("diaChi"));
        baiDo.setGiaTien(new BigDecimal(params.get("giaTien")));
        baiDo.setSoLuong(Integer.parseInt(params.get("soLuong")));
        baiDo.setTienIch(params.get("tienIch"));
//        baiDo.setTrangThai(params.get("trangThai"));
        baiDo.setFile(anhBai);

        return new ResponseEntity<>(this.baiDoService.createOrUpdate(baiDo), HttpStatus.CREATED);
    }

    @PutMapping(path = "/baidos/edit/{id}",
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

            if (ten == null || diaChi == null || soLuongStr == null || giaTienStr == null) {
                return new ResponseEntity<>("Thiếu thông tin bắt buộc", HttpStatus.BAD_REQUEST);
            }

            baiDo.setTen(ten);
            baiDo.setDiaChi(diaChi);
            baiDo.setSoLuong(Integer.parseInt(soLuongStr));
            baiDo.setGiaTien(new BigDecimal(giaTienStr));
            baiDo.setTienIch(tienIch);

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
