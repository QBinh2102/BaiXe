/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.controllers;

import com.bxtm.pojo.Nguoidung;
import com.bxtm.services.NguoidungService;
import com.bxtm.utils.JwtUtils;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author toquocbinh2102
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiNguoidungController {
    @Autowired
    private NguoidungService nguoiDungService;
    
    @PostMapping(path="/nguoidungs",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Nguoidung> register(@RequestParam Map<String,String> params, 
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("anhXe") MultipartFile anhXe){
        Nguoidung newNguoiDung = new Nguoidung();
        newNguoiDung.setHoTen(params.get("hoTen"));
        newNguoiDung.setTaiKhoan(params.get("taiKhoan"));
        newNguoiDung.setMatKhau(params.get("matKhau"));
        newNguoiDung.setEmail(params.get("email"));
        newNguoiDung.setSdt(params.get("sdt"));
        newNguoiDung.setCccd(params.get("cccd"));
        newNguoiDung.setHieuXe(params.get("hieuXe"));
        newNguoiDung.setBienSo(params.get("bienSo"));
        newNguoiDung.setMauXe(params.get("mauXe"));
        newNguoiDung.setFileAnhXe(anhXe);
        newNguoiDung.setFile(avatar);
        newNguoiDung.setVaiTro("ROLE_USER");
        newNguoiDung.setActive(Boolean.TRUE);
        
        return new ResponseEntity<>(this.nguoiDungService.createOrUpdate(newNguoiDung), HttpStatus.CREATED);
    }
    
    @PutMapping("/admin/nguoidungs/{idNguoiDung}")
    public ResponseEntity<Nguoidung> updateNguoiDung(@RequestParam Map<String,String> params,
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("anhXe") MultipartFile anhXe){
        Nguoidung nguoiDung = new Nguoidung();
        nguoiDung.setHoTen(params.get("hoTen"));
        nguoiDung.setTaiKhoan(params.get("taiKhoan"));
        nguoiDung.setMatKhau(params.get("matKhau"));
        nguoiDung.setEmail(params.get("email"));
        nguoiDung.setSdt(params.get("sdt"));
        nguoiDung.setCccd(params.get("cccd"));
        nguoiDung.setHieuXe(params.get("hieuXe"));
        nguoiDung.setBienSo(params.get("bienSo"));
        nguoiDung.setMauXe(params.get("mauXe"));
        nguoiDung.setFileAnhXe(anhXe);
        nguoiDung.setFile(avatar);
        nguoiDung.setVaiTro("ROLE_USER");
        nguoiDung.setActive(Boolean.TRUE);
        
        return new ResponseEntity<>(this.nguoiDungService.createOrUpdate(nguoiDung), HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Nguoidung nguoiDung) {

        if (this.nguoiDungService.authenticate(nguoiDung.getTaiKhoan(), nguoiDung.getMatKhau())) {
            try {
                String token = JwtUtils.generateToken(nguoiDung.getTaiKhoan());
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
    }

    @RequestMapping("/secure/profile")
    @ResponseBody
    public ResponseEntity<Nguoidung> getProfile(Principal principal) {
        return new ResponseEntity<>(this.nguoiDungService.getNguoiDungByTaiKhoan(principal.getName()), HttpStatus.OK);
    }
}
