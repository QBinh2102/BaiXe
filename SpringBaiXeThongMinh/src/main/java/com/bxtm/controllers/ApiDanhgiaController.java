/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Danhgia;
import com.bxtm.pojo.Nguoidung;
import com.bxtm.services.BaidoService;
import com.bxtm.services.DanhgiaService;
import com.bxtm.services.NguoidungService;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@CrossOrigin
public class ApiDanhgiaController {

    @Autowired
    private DanhgiaService danhGiaService;
    @Autowired
    private BaidoService baiDoService;
    @Autowired
    private NguoidungService nguoiDungService;

    @GetMapping("/baidos/{idBaiDo}/danhgias/")
    public ResponseEntity<List<Danhgia>> getDanhGiasByBaiDo(@PathVariable("idBaiDo") int idBaiDo) {
        Map<String, String> params = new HashMap<>();
        params.put("idBaiDo", String.valueOf(idBaiDo));
        List<Danhgia> danhGias = this.danhGiaService.getDanhGia(params);
        return new ResponseEntity<>(danhGias, HttpStatus.OK);
    }

    @PostMapping("/secure/me/baidos/{idBaiDo}/danhgias/")
    public ResponseEntity<Danhgia> create(@RequestParam Map<String, String> params,
            Principal principal,
            @PathVariable("idBaiDo") int idBaiDo) {
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungByTaiKhoan(principal.getName());
        int rating = Integer.parseInt(params.get("rating"));
        String binhLuan = params.get("binhLuan");

        Danhgia danhGiaMoi = new Danhgia();
        danhGiaMoi.setRating(rating);  // tùy thuộc tên trường trong POJO
        danhGiaMoi.setBinhLuan(binhLuan);

        danhGiaMoi.setIdBaiDo(this.baiDoService.getBaiDoById(idBaiDo));
        danhGiaMoi.setIdNguoiDung(nguoiDung);

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            danhGiaMoi.setThoiGianDanhGia(formatter.parse(params.get("thoiGianDanhGia")));

        } catch (ParseException e) {
            // Xử lý lỗi
        }
        Danhgia savedDanhGia = this.danhGiaService.createOrUpdate(danhGiaMoi);
        return new ResponseEntity<>(savedDanhGia, HttpStatus.CREATED);
    }

    @PatchMapping("/secure/me/baidos/{idBaiDo}/danhgias/")
    public ResponseEntity<Danhgia> updateDanhGia(Principal principal,
            @PathVariable("idBaiDo") int idBaiDo,
            @RequestParam Map<String, String> params) {

        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungByTaiKhoan(principal.getName());
        int rating = Integer.parseInt(params.get("rating"));
        String binhLuan = params.get("binhLuan");

        Danhgia existing = this.danhGiaService.getDanhGiaById(Integer.parseInt(params.get("idDanhGia")));
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (existing.getIdNguoiDung().equals(nguoiDung)) {
            existing.setRating(rating);
            existing.setBinhLuan(binhLuan);

            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                existing.setThoiGianDanhGia(formatter.parse(params.get("thoiGianDanhGia")));
            } catch (ParseException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        Danhgia updated = this.danhGiaService.createOrUpdate(existing);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/secure/me/danhgias/{idDanhGia}/")
    public ResponseEntity<?> deleteDanhGia(Principal principal, @PathVariable("idDanhGia") int idDanhGia) {
        try {
            Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungByTaiKhoan(principal.getName());
            Danhgia existing = this.danhGiaService.getDanhGiaById(idDanhGia);
            if (existing == null || !existing.getIdNguoiDung().equals(nguoiDung)) {
                return new ResponseEntity<>(
                        Map.of("error", "Đánh giá không tồn tại"),
                        HttpStatus.NOT_FOUND
                );
            }
            this.danhGiaService.deleteDanhGia(idDanhGia);
            return new ResponseEntity<>(
                    Map.of("message", "Xóa đánh giá thành công"),
                    HttpStatus.OK
            );

        } catch (Exception e) {
            return new ResponseEntity<>(
                    Map.of("error", "Có lỗi xảy ra khi xóa đánh giá: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
