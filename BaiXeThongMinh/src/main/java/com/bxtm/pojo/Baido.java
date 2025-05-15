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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "baido")
@NamedQueries({
    @NamedQuery(name = "Baido.findAll", query = "SELECT b FROM Baido b"),
    @NamedQuery(name = "Baido.findById", query = "SELECT b FROM Baido b WHERE b.id = :id"),
    @NamedQuery(name = "Baido.findByTen", query = "SELECT b FROM Baido b WHERE b.ten = :ten"),
    @NamedQuery(name = "Baido.findByDiaChi", query = "SELECT b FROM Baido b WHERE b.diaChi = :diaChi"),
    @NamedQuery(name = "Baido.findBySoLuong", query = "SELECT b FROM Baido b WHERE b.soLuong = :soLuong"),
    @NamedQuery(name = "Baido.findByTrangThai", query = "SELECT b FROM Baido b WHERE b.trangThai = :trangThai")})
public class Baido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ten")
    private String ten;
    @Column(name = "diaChi")
    private String diaChi;
    @Column(name = "soLuong")
    private Integer soLuong;
    @Lob
    @Column(name = "tienIch")
    private String tienIch;
    @Column(name = "trangThai")
    private String trangThai;
    @OneToMany(mappedBy = "idBaiDo")
    private Set<Chodo> chodoSet;
    @OneToMany(mappedBy = "idBaiDo")
    private Set<Giatien> giatienSet;
    @OneToMany(mappedBy = "idBaiDo")
    private Set<Anhbai> anhbaiSet;
    @OneToMany(mappedBy = "idBaiDo")
    private Set<Danhgia> danhgiaSet;
    @OneToMany(mappedBy = "idBaiDo")
    private Set<Baotri> baotriSet;

    public Baido() {
    }

    public Baido(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getTienIch() {
        return tienIch;
    }

    public void setTienIch(String tienIch) {
        this.tienIch = tienIch;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Set<Chodo> getChodoSet() {
        return chodoSet;
    }

    public void setChodoSet(Set<Chodo> chodoSet) {
        this.chodoSet = chodoSet;
    }

    public Set<Giatien> getGiatienSet() {
        return giatienSet;
    }

    public void setGiatienSet(Set<Giatien> giatienSet) {
        this.giatienSet = giatienSet;
    }

    public Set<Anhbai> getAnhbaiSet() {
        return anhbaiSet;
    }

    public void setAnhbaiSet(Set<Anhbai> anhbaiSet) {
        this.anhbaiSet = anhbaiSet;
    }

    public Set<Danhgia> getDanhgiaSet() {
        return danhgiaSet;
    }

    public void setDanhgiaSet(Set<Danhgia> danhgiaSet) {
        this.danhgiaSet = danhgiaSet;
    }

    public Set<Baotri> getBaotriSet() {
        return baotriSet;
    }

    public void setBaotriSet(Set<Baotri> baotriSet) {
        this.baotriSet = baotriSet;
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
        if (!(object instanceof Baido)) {
            return false;
        }
        Baido other = (Baido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Baido[ id=" + id + " ]";
    }
    
}
