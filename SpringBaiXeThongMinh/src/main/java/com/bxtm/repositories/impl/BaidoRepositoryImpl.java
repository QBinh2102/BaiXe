package com.bxtm.repositories.impl;

import com.bxtm.pojo.Baido;
import com.bxtm.pojo.Chodo;
import com.bxtm.pojo.Danhgia;
import com.bxtm.repositories.BaidoRepository;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author GIGA
 */
@Repository
@Transactional
public class BaidoRepositoryImpl implements BaidoRepository {

    private static final int PAGE_SIZE = 6;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Baido> getBaiDo(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Baido> q = cb.createQuery(Baido.class);
        Root root = q.from(Baido.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String ten = params.get("tenBai");
            String diaChi = params.get("diaChi");

            if (ten != null && !ten.isEmpty() && diaChi != null && !diaChi.isEmpty()) {
                predicates.add(
                        cb.and(
                                cb.like(root.get("ten"), String.format("%%%s%%", ten)),
                                cb.like(root.get("diaChi"), String.format("%%%s%%", diaChi))
                        )
                );
            } else if (ten != null && !ten.isEmpty()) {
                predicates.add(cb.like(root.get("ten").as(String.class), String.format("%%%s%%", ten)));
            } else if (diaChi != null && !diaChi.isEmpty()) {
                predicates.add(cb.like(root.get("diaChi").as(String.class), String.format("%%%s%%", diaChi)));
            }
            
            String fromPrice = params.get("fromPrice");
            if (fromPrice != null && !fromPrice.isEmpty()) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("giaTien"), fromPrice));
            }

            String toPrice = params.get("toPrice");
            if (toPrice != null && !toPrice.isEmpty()) {
                predicates.add(cb.lessThanOrEqualTo(root.get("giaTien"), toPrice));
            }

            String trangThai = params.get("trangThai");
            if (trangThai != null && !trangThai.isEmpty()) {
                predicates.add(cb.equal(root.get("trangThai").as(String.class), trangThai));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        Query query = s.createQuery(q);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.getOrDefault("page", "1"));
            int start = (page - 1) * PAGE_SIZE;
            query.setMaxResults(PAGE_SIZE);
            query.setFirstResult(start);
        }

        return query.getResultList();
    }

    @Override
    public Baido getBaiDoById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Baido.class, id);
    }

    @Override
    public Baido createOrUpdate(Baido baiDo) {
        Session s = this.factory.getObject().getCurrentSession();
        if (baiDo.getId() == null) {
            s.persist(baiDo);
        } else {
            s.merge(baiDo);
        }

        s.refresh(baiDo);

        return baiDo;
    }

//    public void deleteBaiDo(int id) {
//        try (Session s = HibernateUtils.getFACTORY().openSession()) {
//            Baido baiDo = this.getBaiDoById(id);
//            s.remove(baiDo);
//        }
//    }
}
