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
@Table(name = "loaingay")
@NamedQueries({
    @NamedQuery(name = "Loaingay.findAll", query = "SELECT l FROM Loaingay l"),
    @NamedQuery(name = "Loaingay.findById", query = "SELECT l FROM Loaingay l WHERE l.id = :id"),
    @NamedQuery(name = "Loaingay.findByTenLe", query = "SELECT l FROM Loaingay l WHERE l.tenLe = :tenLe"),
    @NamedQuery(name = "Loaingay.findByNgayApDung", query = "SELECT l FROM Loaingay l WHERE l.ngayApDung = :ngayApDung"),
    @NamedQuery(name = "Loaingay.findByNgayKetThuc", query = "SELECT l FROM Loaingay l WHERE l.ngayKetThuc = :ngayKetThuc")})
public class Loaingay implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tenLe")
    private String tenLe;
    @Column(name = "ngayApDung")
    @Temporal(TemporalType.DATE)
    private Date ngayApDung;
    @Column(name = "ngayKetThuc")
    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;
    @OneToMany(mappedBy = "idLoaiNgay")
    private Set<Giatien> giatienSet;

    public Loaingay() {
    }

    public Loaingay(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenLe() {
        return tenLe;
    }

    public void setTenLe(String tenLe) {
        this.tenLe = tenLe;
    }

    public Date getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(Date ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Set<Giatien> getGiatienSet() {
        return giatienSet;
    }

    public void setGiatienSet(Set<Giatien> giatienSet) {
        this.giatienSet = giatienSet;
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
        if (!(object instanceof Loaingay)) {
            return false;
        }
        Loaingay other = (Loaingay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Loaingay[ id=" + id + " ]";
    }
    
}
