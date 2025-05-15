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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "booking")
@NamedQueries({
    @NamedQuery(name = "Booking.findAll", query = "SELECT b FROM Booking b"),
    @NamedQuery(name = "Booking.findById", query = "SELECT b FROM Booking b WHERE b.id = :id"),
    @NamedQuery(name = "Booking.findByThoiGianDat", query = "SELECT b FROM Booking b WHERE b.thoiGianDat = :thoiGianDat"),
    @NamedQuery(name = "Booking.findByThoiGianBatDau", query = "SELECT b FROM Booking b WHERE b.thoiGianBatDau = :thoiGianBatDau"),
    @NamedQuery(name = "Booking.findByThoiGianKetThuc", query = "SELECT b FROM Booking b WHERE b.thoiGianKetThuc = :thoiGianKetThuc"),
    @NamedQuery(name = "Booking.findByTrangThai", query = "SELECT b FROM Booking b WHERE b.trangThai = :trangThai")})
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "thoiGianDat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianDat;
    @Column(name = "thoiGianBatDau")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianBatDau;
    @Column(name = "thoiGianKetThuc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianKetThuc;
    @Column(name = "trangThai")
    private String trangThai;
    @JoinColumn(name = "idChoDo", referencedColumnName = "id")
    @ManyToOne
    private Chodo idChoDo;
    @JoinColumn(name = "idGiaTien", referencedColumnName = "id")
    @ManyToOne
    private Giatien idGiaTien;
    @JoinColumn(name = "idNguoiDung", referencedColumnName = "id")
    @ManyToOne
    private Nguoidung idNguoiDung;
    @OneToMany(mappedBy = "idBooking")
    private Set<Hoadon> hoadonSet;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getThoiGianDat() {
        return thoiGianDat;
    }

    public void setThoiGianDat(Date thoiGianDat) {
        this.thoiGianDat = thoiGianDat;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Chodo getIdChoDo() {
        return idChoDo;
    }

    public void setIdChoDo(Chodo idChoDo) {
        this.idChoDo = idChoDo;
    }

    public Giatien getIdGiaTien() {
        return idGiaTien;
    }

    public void setIdGiaTien(Giatien idGiaTien) {
        this.idGiaTien = idGiaTien;
    }

    public Nguoidung getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(Nguoidung idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public Set<Hoadon> getHoadonSet() {
        return hoadonSet;
    }

    public void setHoadonSet(Set<Hoadon> hoadonSet) {
        this.hoadonSet = hoadonSet;
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
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Booking[ id=" + id + " ]";
    }
    
}
