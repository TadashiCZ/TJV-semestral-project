/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.tadeasvalenta.semestralka.konzument.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tadas
 */
@Entity
@XmlRootElement
public class Plug implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "PROVIDER")
    private String provider;
    
    @Column(name = "PAYMENT")
    private Long payment;
    
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private PlugType type;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="FLAT_ID")
    private Flat flat;
    // TODO flat mapping

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public PlugType getType() {
        return type;
    }

    public void setType(PlugType type) {
        this.type = type;
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
        if (!(object instanceof Plug)) {
            return false;
        }
        Plug other = (Plug) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities.Plug[ id=" + id + " ]";
    }
    

}
