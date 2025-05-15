/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "anhbai")
@NamedQueries({
    @NamedQuery(name = "Anhbai.findAll", query = "SELECT a FROM Anhbai a"),
    @NamedQuery(name = "Anhbai.findById", query = "SELECT a FROM Anhbai a WHERE a.id = :id"),
    @NamedQuery(name = "Anhbai.findByAnhBai", query = "SELECT a FROM Anhbai a WHERE a.anhBai = :anhBai")})
public class Anhbai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "anhBai")
    private String anhBai;
    @JoinColumn(name = "idBaiDo", referencedColumnName = "id")
    @ManyToOne
    private Baido idBaiDo;

    public Anhbai() {
    }

    public Anhbai(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnhBai() {
        return anhBai;
    }

    public void setAnhBai(String anhBai) {
        this.anhBai = anhBai;
    }

    public Baido getIdBaiDo() {
        return idBaiDo;
    }

    public void setIdBaiDo(Baido idBaiDo) {
        this.idBaiDo = idBaiDo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anhbai)) {
            return false;
        }
        Anhbai other = (Anhbai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Anhbai[ id=" + id + " ]";
    }
    
}
