/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.repositories.impl;

import com.bxtm.pojo.Baotri;
import com.bxtm.repositories.BaotriRepository;
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
 * @author toquocbinh2102
 */
@Repository
@Transactional
public class BaotriRepositoryImpl implements BaotriRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Baotri> getBaoTri(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Baotri> q = cb.createQuery(Baotri.class);
        Root root = q.from(Baotri.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String tinhTrang = params.get("tinhTrang");
            if (tinhTrang != null && !tinhTrang.isEmpty()) {
                predicates.add(cb.equal(root.get("tinhTrang").as(String.class), tinhTrang));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }
        Query query = s.createQuery(q);
        return query.getResultList();
    }
    
    @Override
    public Baotri getBaoTriById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Baotri.class, id);
    }

    @Override
    public Baotri createOrUpdate(Baotri baoTri) {
        Session s = this.factory.getObject().getCurrentSession();
        if (baoTri.getId() == null) {
            s.persist(baoTri);
        } else {
            s.merge(baoTri);
        }

        s.refresh(baoTri);

        return baoTri;
    }
}
