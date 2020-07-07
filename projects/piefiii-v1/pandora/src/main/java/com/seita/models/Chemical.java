/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seita.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author estevaofay
 */
@Entity
@Table(name = "Chemical")
@DiscriminatorValue("C")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chemical.findAll", query = "SELECT c FROM Chemical c"),
    @NamedQuery(name = "Chemical.findByFormula", query = "SELECT c FROM Chemical c WHERE c.formula = :formula"),
    @NamedQuery(name = "Chemical.findByUnit", query = "SELECT c FROM Chemical c WHERE c.unit = :unit"),
    @NamedQuery(name = "Chemical.findByExpirationDAte", query = "SELECT c FROM Chemical c WHERE c.expirationDAte = :expirationDAte"),
    @NamedQuery(name = "Chemical.findBySafetySheet", query = "SELECT c FROM Chemical c WHERE c.safetySheet = :safetySheet")})
public class Chemical extends Material implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "formula")
    private String formula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expirationDAte")
    @Temporal(TemporalType.DATE)
    private Date expirationDAte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "safetySheet")
    private String safetySheet;

    public Chemical() {
    }

//    public Chemical(Integer materialidMaterial) {
//        this.materialidMaterial = materialidMaterial;
//    }
//
//    public Chemical(Integer materialidMaterial, String formula, String unit, Date expirationDAte, String safetySheet) {
//        this.materialidMaterial = materialidMaterial;
//        this.formula = formula;
//        this.unit = unit;
//        this.expirationDAte = expirationDAte;
//        this.safetySheet = safetySheet;
//    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getExpirationDAte() {
        return expirationDAte;
    }

    public void setExpirationDAte(Date expirationDAte) {
        this.expirationDAte = expirationDAte;
    }

    public String getSafetySheet() {
        return safetySheet;
    }

    public void setSafetySheet(String safetySheet) {
        this.safetySheet = safetySheet;
    }

//    public Integer getMaterialidMaterial() {
//        return materialidMaterial;
//    }
//
//    public void setMaterialidMaterial(Integer materialidMaterial) {
//        this.materialidMaterial = materialidMaterial;
//    }
//
//    public Material getMaterial() {
//        return material;
//    }
//
//    public void setMaterial(Material material) {
//        this.material = material;
//    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        hash += (materialidMaterial != null ? materialidMaterial.hashCode() : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof Chemical)) {
//            return false;
//        }
//        Chemical other = (Chemical) object;
//        if ((this.materialidMaterial == null && other.materialidMaterial != null) || (this.materialidMaterial != null && !this.materialidMaterial.equals(other.materialidMaterial))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "com.seita.models.Chemical[ materialidMaterial=" + materialidMaterial + " ]";
//    }

}
