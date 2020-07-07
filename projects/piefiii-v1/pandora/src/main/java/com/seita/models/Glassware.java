/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estevaofay
 */
@Entity
@Table(name = "Glassware")
@DiscriminatorValue("G")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Glassware.findAll", query = "SELECT g FROM Glassware g")
    , @NamedQuery(name = "Glassware.findByCapacity", query = "SELECT g FROM Glassware g WHERE g.capacity = :capacity")})
public class Glassware extends Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacity")
    private int capacity;
    
    /*@Basic(optional = false)
    @NotNull
    @Column(name = "Material_idMaterial")
    private Integer materialidMaterial;
    /*@JoinColumn(name = "Material_idMaterial", referencedColumnName = "idMaterial", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Material material;*/

    public Glassware() {
    }

   /* public Glassware(Integer materialidMaterial) {
        this.materialidMaterial = materialidMaterial;
    }

    public Glassware(Integer materialidMaterial, int capacity) {
        this.materialidMaterial = materialidMaterial;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getMaterialidMaterial() {
        return materialidMaterial;
    }

    public void setMaterialidMaterial(Integer materialidMaterial) {
        this.materialidMaterial = materialidMaterial;
    }

    /*public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialidMaterial != null ? materialidMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Glassware)) {
            return false;
        }
        Glassware other = (Glassware) object;
        if ((this.materialidMaterial == null && other.materialidMaterial != null) || (this.materialidMaterial != null && !this.materialidMaterial.equals(other.materialidMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.seita.models.Glassware[ materialidMaterial=" + materialidMaterial + " ]";
    }*/
    
}
