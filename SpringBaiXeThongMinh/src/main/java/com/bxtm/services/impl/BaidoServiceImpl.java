/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.services.impl;

import com.bxtm.pojo.Baido;
import com.bxtm.pojo.Chodo;
import com.bxtm.pojo.Danhgia;
import com.bxtm.repositories.BaidoRepository;
import com.bxtm.repositories.ChodoRepository;
import com.bxtm.services.BaidoService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author toquocbinh2102
 */
@Service
public class BaidoServiceImpl implements BaidoService {

    @Autowired
    private BaidoRepository baiDoRepo;
    @Autowired
    private ChodoRepository choDoRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Baido> getBaiDo(Map<String, String> params) {
        return this.baiDoRepo.getBaiDo(params);
    }

    @Override
    public Baido getBaiDoById(int id) {
        Baido baiDo = this.baiDoRepo.getBaiDoById(id);
        baiDo.setFile(null);
        return baiDo;
    }

    @Override
    public Baido createOrUpdate(Baido baiDo) {
        boolean isNew = baiDo.getId() == null;

        if (isNew) {
            baiDo.setTrangThai("Hoạt động");
        }

        // Nếu là cập nhật và không có ảnh mới -> giữ nguyên ảnh cũ
        if (!isNew && (baiDo.getFile() == null || baiDo.getFile().isEmpty())) {
            Baido old = this.baiDoRepo.getBaiDoById(baiDo.getId());
            baiDo.setAnhBai(old.getAnhBai());
        } else if (baiDo.getFile() != null && !baiDo.getFile().isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(baiDo.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                baiDo.setAnhBai(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(BaidoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Lưu bãi đỗ
        baiDo = this.baiDoRepo.createOrUpdate(baiDo);

        // Nếu thêm mới, tạo chỗ đỗ tương ứng
        if (isNew && baiDo.getSoLuong() > 0) {
            for (int i = 1; i <= baiDo.getSoLuong(); i++) {
                Chodo chodo = new Chodo();
                chodo.setViTri(String.valueOf(i));
                chodo.setTrangThai("Bình thường");
                chodo.setIdBaiDo(baiDo);
                this.choDoRepo.createOrUpdate(chodo);
            }
        }

        return baiDo;
    }

}
