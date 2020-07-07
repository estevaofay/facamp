/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estevaofay
 */
@Entity
@Table(name = "Material")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m")
    , @NamedQuery(name = "Material.findByIdMaterial", query = "SELECT m FROM Material m WHERE m.idMaterial = :idMaterial")
    , @NamedQuery(name = "Material.findByName", query = "SELECT m FROM Material m WHERE m.name = :name")
    , @NamedQuery(name = "Material.findByPhoto", query = "SELECT m FROM Material m WHERE m.photo = :photo")
    , @NamedQuery(name = "Material.findByQuantity", query = "SELECT m FROM Material m WHERE m.quantity = :quantity")})
public abstract class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMaterial")
    private Integer idMaterial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "photo")
    private String photo;
    @NotNull
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinTable(name = "Reservation_has_Material", joinColumns = {
        @JoinColumn(name = "Material_idMaterial", referencedColumnName = "idMaterial")}, inverseJoinColumns = {
        @JoinColumn(name = "Reservation_idReservation", referencedColumnName = "idReservation")})
    @ManyToMany
    private Collection<Reservation> reservationCollection;

    public Material() {
    }

    public Material(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Material(Integer idMaterial, String name, String photo, int quantity) {
        this.idMaterial = idMaterial;
        this.name = name;
        this.photo = photo;
        this.quantity = quantity;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seita.models.Material[ idMaterial=" + idMaterial + " ]";
    }
    
}
