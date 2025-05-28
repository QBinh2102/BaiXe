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
    
    @GetMapping("/nguoidungs")
    public ResponseEntity<List<Nguoidung>> getNguoiDung(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.nguoiDungService.getNguoiDung(params), HttpStatus.OK);
    }
    
    @GetMapping("/nguoidungs/{id}")
    public ResponseEntity<Nguoidung> getNguoiDungById(@PathVariable("id") int id) {
        return new ResponseEntity<>(this.nguoiDungService.getNguoiDungById(id), HttpStatus.OK);
    }
    
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
    
    @PatchMapping("/nguoidungs/{idNguoiDung}/capnhat")
    public ResponseEntity<Nguoidung> updateNguoiDung(@PathVariable(value="idNguoiDung") int id,
            @RequestParam Map<String,String> params,
            @RequestParam("avatar") MultipartFile avatar,
            @RequestParam("anhXe") MultipartFile anhXe){
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungById(id);
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
        
        return new ResponseEntity<>(this.nguoiDungService.createOrUpdate(nguoiDung), HttpStatus.OK);
    }
    
    @PatchMapping("/nguoidungs/{idNguoiDung}/capnhatanhxe")
    public ResponseEntity<Nguoidung> updateNguoiDungAnhXe(@PathVariable(value="idNguoiDung") int id,
            @RequestParam Map<String,String> params,
            @RequestParam("anhXe") MultipartFile anhXe){
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungById(id);
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
        
        return new ResponseEntity<>(this.nguoiDungService.createOrUpdate(nguoiDung), HttpStatus.OK);
    }
    
    @PatchMapping("/nguoidungs/{idNguoiDung}/capnhatavatar")
    public ResponseEntity<Nguoidung> updateNguoiDungAvatar(@PathVariable(value="idNguoiDung") int id,
            @RequestParam Map<String,String> params,
            @RequestParam("avatar") MultipartFile avatar){
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungById(id);
        nguoiDung.setHoTen(params.get("hoTen"));
        nguoiDung.setTaiKhoan(params.get("taiKhoan"));
        nguoiDung.setMatKhau(params.get("matKhau"));
        nguoiDung.setEmail(params.get("email"));
        nguoiDung.setSdt(params.get("sdt"));
        nguoiDung.setCccd(params.get("cccd"));
        nguoiDung.setHieuXe(params.get("hieuXe"));
        nguoiDung.setBienSo(params.get("bienSo"));
        nguoiDung.setMauXe(params.get("mauXe"));
        nguoiDung.setFile(avatar);
        
        return new ResponseEntity<>(this.nguoiDungService.createOrUpdate(nguoiDung), HttpStatus.OK);
    }
    
    @PatchMapping("/nguoidungs/{idNguoiDung}/capnhatkhonganh")
    public ResponseEntity<Nguoidung> updateNguoiDungKhongAnh(@PathVariable(value="idNguoiDung") int id,
            @RequestParam Map<String,String> params){
        Nguoidung nguoiDung = this.nguoiDungService.getNguoiDungById(id);
        nguoiDung.setHoTen(params.get("hoTen"));
        nguoiDung.setTaiKhoan(params.get("taiKhoan"));
        
         if (params.get("matKhau") != null || !params.get("matKhau").isEmpty() || params.get("matKhau").equals("")) {
            nguoiDung.setMatKhau(params.get("matKhau"));
        }
         
        nguoiDung.setEmail(params.get("email"));
        nguoiDung.setSdt(params.get("sdt"));
        nguoiDung.setCccd(params.get("cccd"));
        nguoiDung.setHieuXe(params.get("hieuXe"));
        nguoiDung.setBienSo(params.get("bienSo"));
        nguoiDung.setMauXe(params.get("mauXe"));
        
         if (params.containsKey("vaiTro")) {
            nguoiDung.setVaiTro(params.get("vaiTro"));
        }
        if (params.containsKey("active")) {
            nguoiDung.setActive(Boolean.valueOf(params.get("active")));
        }
        
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
