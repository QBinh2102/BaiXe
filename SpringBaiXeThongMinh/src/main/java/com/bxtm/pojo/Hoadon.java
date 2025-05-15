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
@Table(name = "hoadon")
@NamedQueries({
    @NamedQuery(name = "Hoadon.findAll", query = "SELECT h FROM Hoadon h"),
    @NamedQuery(name = "Hoadon.findById", query = "SELECT h FROM Hoadon h WHERE h.id = :id"),
    @NamedQuery(name = "Hoadon.findByPhuongThuc", query = "SELECT h FROM Hoadon h WHERE h.phuongThuc = :phuongThuc"),
    @NamedQuery(name = "Hoadon.findByThoiGianThanhToan", query = "SELECT h FROM Hoadon h WHERE h.thoiGianThanhToan = :thoiGianThanhToan"),
    @NamedQuery(name = "Hoadon.findByTrangThai", query = "SELECT h FROM Hoadon h WHERE h.trangThai = :trangThai")})
public class Hoadon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "phuongThuc")
    private String phuongThuc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianThanhToan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianThanhToan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "trangThai")
    private String trangThai;
    @JoinColumn(name = "idBooking", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Booking idBooking;
    @JoinColumn(name = "idNguoiDung", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Nguoidung idNguoiDung;

    public Hoadon() {
    }

    public Hoadon(Integer id) {
        this.id = id;
    }

    public Hoadon(Integer id, String phuongThuc, Date thoiGianThanhToan, String trangThai) {
        this.id = id;
        this.phuongThuc = phuongThuc;
        this.thoiGianThanhToan = thoiGianThanhToan;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public Date getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }

    public void setThoiGianThanhToan(Date thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Booking getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(Booking idBooking) {
        this.idBooking = idBooking;
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
        if (!(object instanceof Hoadon)) {
            return false;
        }
        Hoadon other = (Hoadon) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Hoadon[ id=" + id + " ]";
    }
    
}
