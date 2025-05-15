/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "baotri")
@NamedQueries({
    @NamedQuery(name = "Baotri.findAll", query = "SELECT b FROM Baotri b"),
    @NamedQuery(name = "Baotri.findById", query = "SELECT b FROM Baotri b WHERE b.id = :id"),
    @NamedQuery(name = "Baotri.findByThoiGianBatDau", query = "SELECT b FROM Baotri b WHERE b.thoiGianBatDau = :thoiGianBatDau"),
    @NamedQuery(name = "Baotri.findByThoiGianKetThuc", query = "SELECT b FROM Baotri b WHERE b.thoiGianKetThuc = :thoiGianKetThuc"),
    @NamedQuery(name = "Baotri.findByTinhTrang", query = "SELECT b FROM Baotri b WHERE b.tinhTrang = :tinhTrang")})
public class Baotri implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianBatDau;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianKetThuc;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tinhTrang")
    private String tinhTrang;
    @JoinColumn(name = "idBaiDo", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Baido idBaiDo;

    public Baotri() {
    }

    public Baotri(Integer id) {
        this.id = id;
    }

    public Baotri(Integer id, Date thoiGianBatDau, Date thoiGianKetThuc, String tinhTrang) {
        this.id = id;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.tinhTrang = tinhTrang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
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
        if (!(object instanceof Baotri)) {
            return false;
        }
        Baotri other = (Baotri) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Baotri[ id=" + id + " ]";
    }
    
}
