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
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "soden")
@NamedQueries({
    @NamedQuery(name = "Soden.findAll", query = "SELECT s FROM Soden s"),
    @NamedQuery(name = "Soden.findById", query = "SELECT s FROM Soden s WHERE s.id = :id"),
    @NamedQuery(name = "Soden.findBySoLanViPham", query = "SELECT s FROM Soden s WHERE s.soLanViPham = :soLanViPham")})
public class Soden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "soLanViPham")
    private Integer soLanViPham;
    @JoinColumn(name = "idNguoiDung", referencedColumnName = "id")
    @ManyToOne
    private Nguoidung idNguoiDung;

    public Soden() {
    }

    public Soden(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSoLanViPham() {
        return soLanViPham;
    }

    public void setSoLanViPham(Integer soLanViPham) {
        this.soLanViPham = soLanViPham;
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
        if (!(object instanceof Soden)) {
            return false;
        }
        Soden other = (Soden) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Soden[ id=" + id + " ]";
    }
    
}
