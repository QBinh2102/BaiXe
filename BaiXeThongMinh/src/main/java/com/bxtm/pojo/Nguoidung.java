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
@Table(name = "nguoidung")
@NamedQueries({
    @NamedQuery(name = "Nguoidung.findAll", query = "SELECT n FROM Nguoidung n"),
    @NamedQuery(name = "Nguoidung.findById", query = "SELECT n FROM Nguoidung n WHERE n.id = :id"),
    @NamedQuery(name = "Nguoidung.findByHoTen", query = "SELECT n FROM Nguoidung n WHERE n.hoTen = :hoTen"),
    @NamedQuery(name = "Nguoidung.findByTaiKhoan", query = "SELECT n FROM Nguoidung n WHERE n.taiKhoan = :taiKhoan"),
    @NamedQuery(name = "Nguoidung.findByMatKhau", query = "SELECT n FROM Nguoidung n WHERE n.matKhau = :matKhau"),
    @NamedQuery(name = "Nguoidung.findByEmail", query = "SELECT n FROM Nguoidung n WHERE n.email = :email"),
    @NamedQuery(name = "Nguoidung.findBySdt", query = "SELECT n FROM Nguoidung n WHERE n.sdt = :sdt"),
    @NamedQuery(name = "Nguoidung.findByCccd", query = "SELECT n FROM Nguoidung n WHERE n.cccd = :cccd"),
    @NamedQuery(name = "Nguoidung.findByHieuXe", query = "SELECT n FROM Nguoidung n WHERE n.hieuXe = :hieuXe"),
    @NamedQuery(name = "Nguoidung.findByBienSo", query = "SELECT n FROM Nguoidung n WHERE n.bienSo = :bienSo"),
    @NamedQuery(name = "Nguoidung.findByMauXe", query = "SELECT n FROM Nguoidung n WHERE n.mauXe = :mauXe"),
    @NamedQuery(name = "Nguoidung.findByAnhXe", query = "SELECT n FROM Nguoidung n WHERE n.anhXe = :anhXe"),
    @NamedQuery(name = "Nguoidung.findByAvatar", query = "SELECT n FROM Nguoidung n WHERE n.avatar = :avatar"),
    @NamedQuery(name = "Nguoidung.findByVaiTro", query = "SELECT n FROM Nguoidung n WHERE n.vaiTro = :vaiTro"),
    @NamedQuery(name = "Nguoidung.findByNgayTao", query = "SELECT n FROM Nguoidung n WHERE n.ngayTao = :ngayTao"),
    @NamedQuery(name = "Nguoidung.findByActive", query = "SELECT n FROM Nguoidung n WHERE n.active = :active")})
public class Nguoidung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "hoTen")
    private String hoTen;
    @Column(name = "taiKhoan")
    private String taiKhoan;
    @Column(name = "matKhau")
    private String matKhau;
    @Column(name = "email")
    private String email;
    @Column(name = "SDT")
    private String sdt;
    @Column(name = "cccd")
    private String cccd;
    @Column(name = "hieuXe")
    private String hieuXe;
    @Column(name = "bienSo")
    private String bienSo;
    @Column(name = "mauXe")
    private String mauXe;
    @Column(name = "anhXe")
    private String anhXe;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "vaiTro")
    private Boolean vaiTro;
    @Column(name = "ngayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "idNguoiDung")
    private Set<Booking> bookingSet;
    @OneToMany(mappedBy = "idNguoiDung")
    private Set<Thongbao> thongbaoSet;
    @OneToMany(mappedBy = "idNguoiDung")
    private Set<Hoadon> hoadonSet;
    @OneToMany(mappedBy = "idNguoiDung")
    private Set<Soden> sodenSet;
    @OneToMany(mappedBy = "idNguoiDung")
    private Set<Danhgia> danhgiaSet;

    public Nguoidung() {
    }

    public Nguoidung(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getHieuXe() {
        return hieuXe;
    }

    public void setHieuXe(String hieuXe) {
        this.hieuXe = hieuXe;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getMauXe() {
        return mauXe;
    }

    public void setMauXe(String mauXe) {
        this.mauXe = mauXe;
    }

    public String getAnhXe() {
        return anhXe;
    }

    public void setAnhXe(String anhXe) {
        this.anhXe = anhXe;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(Boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public Set<Thongbao> getThongbaoSet() {
        return thongbaoSet;
    }

    public void setThongbaoSet(Set<Thongbao> thongbaoSet) {
        this.thongbaoSet = thongbaoSet;
    }

    public Set<Hoadon> getHoadonSet() {
        return hoadonSet;
    }

    public void setHoadonSet(Set<Hoadon> hoadonSet) {
        this.hoadonSet = hoadonSet;
    }

    public Set<Soden> getSodenSet() {
        return sodenSet;
    }

    public void setSodenSet(Set<Soden> sodenSet) {
        this.sodenSet = sodenSet;
    }

    public Set<Danhgia> getDanhgiaSet() {
        return danhgiaSet;
    }

    public void setDanhgiaSet(Set<Danhgia> danhgiaSet) {
        this.danhgiaSet = danhgiaSet;
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
        if (!(object instanceof Nguoidung)) {
            return false;
        }
        Nguoidung other = (Nguoidung) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Nguoidung[ id=" + id + " ]";
    }
    
}
