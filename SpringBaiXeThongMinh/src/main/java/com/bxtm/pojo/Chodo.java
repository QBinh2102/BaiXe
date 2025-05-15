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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author toquocbinh2102
 */
@Entity
@Table(name = "chodo")
@NamedQueries({
    @NamedQuery(name = "Chodo.findAll", query = "SELECT c FROM Chodo c"),
    @NamedQuery(name = "Chodo.findById", query = "SELECT c FROM Chodo c WHERE c.id = :id"),
    @NamedQuery(name = "Chodo.findByViTri", query = "SELECT c FROM Chodo c WHERE c.viTri = :viTri"),
    @NamedQuery(name = "Chodo.findByTrangThai", query = "SELECT c FROM Chodo c WHERE c.trangThai = :trangThai")})
public class Chodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "viTri")
    private String viTri;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "trangThai")
    private String trangThai;
    @JoinColumn(name = "idBaiDo", referencedColumnName = "id")
    @ManyToOne
    @JsonIgnore
    private Baido idBaiDo;
    @OneToMany(mappedBy = "idChoDo")
    @JsonIgnore
    private Set<Booking> bookingSet;

    public Chodo() {
    }

    public Chodo(Integer id) {
        this.id = id;
    }

    public Chodo(Integer id, String viTri, String trangThai) {
        this.id = id;
        this.viTri = viTri;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
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

    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
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
        if (!(object instanceof Chodo)) {
            return false;
        }
        Chodo other = (Chodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bxtm.pojo.Chodo[ id=" + id + " ]";
    }
    
}
