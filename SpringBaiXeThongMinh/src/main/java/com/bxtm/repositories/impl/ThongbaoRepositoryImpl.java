/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.pojo.Thongbao;
import com.bxtm.repositories.ThongbaoRepository;
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
public class ThongbaoRepositoryImpl implements ThongbaoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Thongbao> getThongBao(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Thongbao> q = cb.createQuery(Thongbao.class);
        Root root = q.from(Thongbao.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String nguoiDung_id = params.get("idNguoiDung");
            if (nguoiDung_id != null && !nguoiDung_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idNguoiDung").get("id").as(String.class), nguoiDung_id));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        return query.getResultList();
    }
    
    @Override
    public Thongbao getThongBaoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Thongbao.class,id);
    }

    @Override
    public Thongbao createOrUpdate(Thongbao thongBao) {
        Session s = this.factory.getObject().getCurrentSession();
        if (thongBao.getId() == null) {
            s.persist(thongBao);
        } else {
            s.merge(thongBao);
        }

        s.refresh(thongBao);

        return thongBao;
    }
}
