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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

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
    @NamedQuery(name = "Nguoidung.findByActive", query = "SELECT n FROM Nguoidung n WHERE n.active = :active")})
public class Nguoidung implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "hoTen")
    private String hoTen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "taiKhoan")
    private String taiKhoan;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "matKhau")
    private String matKhau;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "SDT")
    private String sdt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "cccd")
    private String cccd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "hieuXe")
    private String hieuXe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "bienSo")
    private String bienSo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mauXe")
    private String mauXe;
    @Size(max = 255)
    @Column(name = "anhXe")
    private String anhXe;
    @Size(max = 255)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "vaiTro")
    private String vaiTro;
    @Column(name = "active")
    private Boolean active;
    @OneToMany(mappedBy = "idNguoiDung")
    @JsonIgnore
    private Set<Booking> bookingSet;
    @OneToMany(mappedBy = "idNguoiDung")
    @JsonIgnore
    private Set<Hoadon> hoadonSet;
    @OneToMany(mappedBy = "idNguoiDung")
    @JsonIgnore
    private Set<Danhgia> danhgiaSet;
    @Transient
    @JsonIgnore
    private MultipartFile file;
    @Transient
    @JsonIgnore
    private MultipartFile fileAnhXe;

    public Nguoidung() {
        this.active = true;
    }

    public Nguoidung(Integer id) {
        this.id = id;
    }

    public Nguoidung(Integer id, String hoTen, String taiKhoan, String matKhau, String email, String sdt, String cccd, String hieuXe, String bienSo, String mauXe, String vaiTro) {
        this.id = id;
        this.hoTen = hoTen;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.sdt = sdt;
        this.cccd = cccd;
        this.hieuXe = hieuXe;
        this.bienSo = bienSo;
        this.mauXe = mauXe;
        this.vaiTro = vaiTro;
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

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
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

    public Set<Hoadon> getHoadonSet() {
        return hoadonSet;
    }

    public void setHoadonSet(Set<Hoadon> hoadonSet) {
        this.hoadonSet = hoadonSet;
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

    /**
     * @return the fileAnhXe
     */
    public MultipartFile getFileAnhXe() {
        return fileAnhXe;
    }

    /**
     * @param fileAnhXe the fileAnhXe to set
     */
    public void setFileAnhXe(MultipartFile fileAnhXe) {
        this.fileAnhXe = fileAnhXe;
    }

}
