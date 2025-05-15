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
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "giatien")
@NamedQueries({
    @NamedQuery(name = "Giatien.findAll", query = "SELECT g FROM Giatien g"),
    @NamedQuery(name = "Giatien.findById", query = "SELECT g FROM Giatien g WHERE g.id = :id"),
    @NamedQuery(name = "Giatien.findByGiaTien", query = "SELECT g FROM Giatien g WHERE g.giaTien = :giaTien"),
    @NamedQuery(name = "Giatien.findByThoiGianApDung", query = "SELECT g FROM Giatien g WHERE g.thoiGianApDung = :thoiGianApDung"),
    @NamedQuery(name = "Giatien.findByThoiGianKetThuc", query = "SELECT g FROM Giatien g WHERE g.thoiGianKetThuc = :thoiGianKetThuc")})
public class Giatien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "giaTien")
    private BigDecimal giaTien;
    @Column(name = "thoiGianApDung")
    @Temporal(TemporalType.TIME)
    private Date thoiGianApDung;
    @Column(name = "thoiGianKetThuc")
    @Temporal(TemporalType.TIME)
    private Date thoiGianKetThuc;
    @OneToMany(mappedBy = "idGiaTien")
    private Set<Booking> bookingSet;
    @JoinColumn(name = "idBaiDo", referencedColumnName = "id")
    @ManyToOne
    private Baido idBaiDo;
    @JoinColumn(name = "idLoaiNgay", referencedColumnName = "id")
    @ManyToOne
    private Loaingay idLoaiNgay;

    public Giatien() {
    }

    public Giatien(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public Date getThoiGianApDung() {
        return thoiGianApDung;
    }

    public void setThoiGianApDung(Date thoiGianApDung) {
        this.thoiGianApDung = thoiGianApDung;
    }

    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public Baido getIdBaiDo() {
        return idBaiDo;
    }

    public void setIdBaiDo(Baido idBaiDo) {
        this.idBaiDo = idBaiDo;
    }

    public Loaingay getIdLoaiNgay() {
        return idLoaiNgay;
    }

    public void setIdLoaiNgay(Loaingay idLoaiNgay) {
        this.idLoaiNgay = idLoaiNgay;
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
        if (!(object instanceof Giatien)) {
            return false;
        }
        Giatien other = (Giatien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Giatien[ id=" + id + " ]";
    }
    
}
