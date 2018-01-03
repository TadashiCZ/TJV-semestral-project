/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities;

import java.io.Serializable;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tadas
 */
@Stateless
@Entity
@XmlRootElement
public class Flat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Column(name = "AREA")
    private Long area;
    
    @Column(name = "DOOR_NUMBER")
    private Long doorNumber;
    
    @Column(name="rent")
    private Long rent;
    
    @OneToOne()
    @JoinColumn(name="OCCUPIER_ID")
    private Occupier occupier;
    
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="flat")
    private Set<Plug> plugs;

    public Occupier getOccupier() {
        return occupier;
    }

    public void setOccupier(Occupier occupier) {
        this.occupier = occupier;
    }

    @XmlTransient
    public Set<Plug> getPlugs() {
        return plugs;
    }
    
    public void setPlugs(Set<Plug> plugs) {
        this.plugs = plugs;
    }

    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }

    public Long getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(Long doorNumber) {
        this.doorNumber = doorNumber;
    }

    public Long getRent() {
        return rent;
    }

    public void setRent(Long rent) {
        this.rent = rent;
    }
      
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Flat)) {
            return false;
        }
        Flat other = (Flat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities.Flat[ id=" + id + " ]";
    }
    
}
