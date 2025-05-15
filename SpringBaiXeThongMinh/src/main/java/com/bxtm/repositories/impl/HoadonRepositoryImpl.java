/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.pojo.Hoadon;
import com.bxtm.repositories.HoadonRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author GIGA
 */
@Repository
@Transactional
public class HoadonRepositoryImpl implements HoadonRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Hoadon> getHoaDon(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Hoadon> q = cb.createQuery(Hoadon.class);
        Root root = q.from(Hoadon.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String booking_id = params.get("idBooking");
            if (booking_id != null && !booking_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idBooking").get("id").as(String.class), booking_id));
            }

            String nguoiDung_id = params.get("idNguoiDung");
            if (nguoiDung_id != null && !nguoiDung_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idNguoiDung").get("id").as(String.class), nguoiDung_id));
            }

            String trangThai = params.get("trangThai");
            if (trangThai != null && !trangThai.isEmpty()) {
                predicates.add(cb.equal(root.get("trangThai").as(String.class), trangThai));
            }

            String phuongThuc = params.get("phuongThuc");
            if (phuongThuc != null && !phuongThuc.isEmpty()) {
                predicates.add(cb.equal(root.get("phuongThuc").as(String.class), phuongThuc));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public Hoadon createOrUpdate(Hoadon hoaDon) {
        Session s = this.factory.getObject().getCurrentSession();
        if (hoaDon.getId() == null) {
            s.persist(hoaDon);
        } else {
            s.merge(hoaDon);
        }

        s.refresh(hoaDon);

        return hoaDon;
    }
}
