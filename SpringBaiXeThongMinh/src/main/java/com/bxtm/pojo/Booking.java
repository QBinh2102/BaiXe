/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bxtm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
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
    @NamedQuery(name = "Booking.findByThanhTien", query = "SELECT b FROM Booking b WHERE b.thanhTien = :thanhTien"),
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "thanhTien")
    private BigDecimal thanhTien;
    @Basic(optional = false)
    @NotNull
    @Column(name = "thoiGianDat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date thoiGianDat;
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
    @Size(min = 1, max = 50)
    @Column(name = "trangThai")
    private String trangThai;
   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idBaiDo", referencedColumnName = "id")
    @JsonIgnoreProperties({"bookingSet", "hibernateLazyInitializer", "handler"})
    private Baido idBaiDo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idChoDo", referencedColumnName = "id")
    @JsonIgnoreProperties({"bookingSet", "hibernateLazyInitializer", "handler"})
    private Chodo idChoDo;
    @JoinColumn(name = "idNguoiDung", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Nguoidung idNguoiDung;
    @OneToMany(mappedBy = "idBooking", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Hoadon> hoadonSet;

    public Booking() {
    }

    public Booking(Integer id) {
        this.id = id;
    }

    public Booking(Integer id, BigDecimal thanhTien, Date thoiGianDat, Date thoiGianBatDau, Date thoiGianKetThuc, String trangThai) {
        this.id = id;
        this.thanhTien = thanhTien;
        this.thoiGianDat = thoiGianDat;
        this.thoiGianBatDau = thoiGianBatDau;
        this.thoiGianKetThuc = thoiGianKetThuc;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
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

    public Baido getIdBaiDo() {
        return idBaiDo;
    }

    public void setIdBaiDo(Baido idBaiDo) {
        this.idBaiDo = idBaiDo;
    }

    public Chodo getIdChoDo() {
        return idChoDo;
    }

    public void setIdChoDo(Chodo idChoDo) {
        this.idChoDo = idChoDo;
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
