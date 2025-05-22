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
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

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
    @NamedQuery(name = "Baido.findByGiaTien", query = "SELECT b FROM Baido b WHERE b.giaTien = :giaTien"),
    @NamedQuery(name = "Baido.findByAnhBai", query = "SELECT b FROM Baido b WHERE b.anhBai = :anhBai"),
    @NamedQuery(name = "Baido.findByTrangThai", query = "SELECT b FROM Baido b WHERE b.trangThai = :trangThai")})
public class Baido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ten")
    private String ten;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "diaChi")
    private String diaChi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "soLuong")
    private int soLuong;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "giaTien")
    private BigDecimal giaTien;
    @Lob
    @Size(max = 65535)
    @Column(name = "tienIch")
    private String tienIch;
    @Size(max = 255)
    @Column(name = "anhBai")
    private String anhBai;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "trangThai")
    private String trangThai;
    @OneToMany(mappedBy = "idBaiDo")
    @JsonIgnore
    private Set<Chodo> chodoSet;
    @OneToMany(mappedBy = "idBaiDo")
    @JsonIgnore
    private Set<Booking> bookingSet;
    @OneToMany(mappedBy = "idBaiDo")
    @JsonIgnore
    private Set<Danhgia> danhgiaSet;
    @OneToMany(mappedBy = "idBaiDo")
    @JsonIgnore
    private Set<Baotri> baotriSet;
    @Transient
    @JsonIgnore
    private MultipartFile file;

    public Baido() {
    }

    public Baido(Integer id) {
        this.id = id;
    }

    public Baido(Integer id, String ten, String diaChi, int soLuong, BigDecimal giaTien, String trangThai) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(BigDecimal giaTien) {
        this.giaTien = giaTien;
    }

    public String getTienIch() {
        return tienIch;
    }

    public void setTienIch(String tienIch) {
        this.tienIch = tienIch;
    }

    public String getAnhBai() {
        return anhBai;
    }

    public void setAnhBai(String anhBai) {
        this.anhBai = anhBai;
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

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
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

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
