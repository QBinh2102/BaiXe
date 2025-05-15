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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "thongbao")
@NamedQueries({
    @NamedQuery(name = "Thongbao.findAll", query = "SELECT t FROM Thongbao t"),
    @NamedQuery(name = "Thongbao.findById", query = "SELECT t FROM Thongbao t WHERE t.id = :id"),
    @NamedQuery(name = "Thongbao.findByThoiGianThongBao", query = "SELECT t FROM Thongbao t WHERE t.thoiGianThongBao = :thoiGianThongBao")})
public class Thongbao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Column(name = "noiDung")
    private String noiDung;
    @Column(name = "thoiGianThongBao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianThongBao;
    @JoinColumn(name = "idNguoiDung", referencedColumnName = "id")
    @ManyToOne
    private Nguoidung idNguoiDung;

    public Thongbao() {
    }

    public Thongbao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getThoiGianThongBao() {
        return thoiGianThongBao;
    }

    public void setThoiGianThongBao(Date thoiGianThongBao) {
        this.thoiGianThongBao = thoiGianThongBao;
    }

    public Nguoidung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(Nguoidung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
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
        if (!(object instanceof Thongbao)) {
            return false;
        }
        Thongbao other = (Thongbao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Thongbao[ id=" + id + " ]";
    }
    
}
