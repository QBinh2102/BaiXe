/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Nguoidung;
import com.bxtm.repositories.NguoidungRepository;
import com.bxtm.services.NguoidungService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author toquocbinh2102
 */
@Service("userDetailService")
@Transactional
public class NguoidungServiceImpl implements NguoidungService{
    @Autowired
    private NguoidungRepository nguoiDungRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Nguoidung> getNguoiDung(Map<String, String> params) {
        return this.nguoiDungRepo.getNguoiDung(params);
    }
    
    @Override
    public Nguoidung getNguoiDungById(int idNguoiDung) {
        return this.nguoiDungRepo.getNguoiDungById(idNguoiDung);
    }

    @Override
    public Nguoidung getNguoiDungByTaiKhoan(String taiKhoan) {
        return this.nguoiDungRepo.getNguoiDungByTaiKhoan(taiKhoan);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Nguoidung nd = this.nguoiDungRepo.getNguoiDungByTaiKhoan(username);
        if(nd == null){
            throw new UsernameNotFoundException("Invalid username!");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(nd.getVaiTro()));
        
        return new org.springframework.security.core.userdetails.User(
                nd.getTaiKhoan(), nd.getMatKhau(), authorities);
    }
    
    @Override
    public Nguoidung createOrUpdate(Nguoidung nguoiDung) {
        nguoiDung.setMatKhau(this.passwordEncoder.encode(nguoiDung.getMatKhau()));
        if(nguoiDung.getFile() != null && !nguoiDung.getFile().isEmpty()){
            try {
                Map res = cloudinary.uploader().upload(nguoiDung.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                nguoiDung.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(NguoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(nguoiDung.getFileAnhXe() != null && !nguoiDung.getFileAnhXe().isEmpty()){
            try {
                Map res = cloudinary.uploader().upload(nguoiDung.getFileAnhXe().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                nguoiDung.setAnhXe(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(NguoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.nguoiDungRepo.createOrUpdate(nguoiDung);
    }

    @Override
    public boolean authenticate(String taiKhoan, String matKhau) {
        return this.nguoiDungRepo.authenticate(taiKhoan, matKhau);
    }
}
