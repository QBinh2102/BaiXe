/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.pojo.Danhgia;
import com.bxtm.repositories.DanhgiaRepository;
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
public class DanhgiaRepositoryImpl implements DanhgiaRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Danhgia> getDanhGia(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Danhgia> q = cb.createQuery(Danhgia.class);
        Root root = q.from(Danhgia.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String baiDo_id = params.get("idBaiDo");
            if (baiDo_id != null && !baiDo_id.isEmpty()) {
                predicates.add(cb.equal(root.get("idBaiDo").get("id"), baiDo_id));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        return query.getResultList();

    }

    @Override
    public Danhgia getDanhGiaById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Danhgia.class, id);
    }
    
//    @Override
//    public List<Danhgia> getDanhGiaByBaiDo(int id) {
//        Session s = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder cb = s.getCriteriaBuilder();
//        CriteriaQuery<Danhgia> q = cb.createQuery(Danhgia.class);
//        Root root = q.from(Danhgia.class);
//        q.select(root);
//        q.where(cb.equal(root.get("idBaiDo").as(Integer.class), id));
//        
//        Query query = s.createQuery(q);
//        return query.getResultList();
//    }

    @Override
    public Danhgia createOrUpdate(Danhgia danhGia) {
        Session s = this.factory.getObject().getCurrentSession();
        if (danhGia.getId() == null) {
            s.persist(danhGia);
        } else {
            s.merge(danhGia);
        }

        s.refresh(danhGia);

        return danhGia;
    }

    @Override
    public void deleteDanhGia(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Danhgia danhGia = this.getDanhGiaById(id);
        s.remove(danhGia);
    }
}
