/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities;

import java.util.List;

/**
 *
 * @author tadas
 */
public class OccupierBox {
     private List<Occupier> occupiers;

    public List<Occupier> getOccupiers() {
        return occupiers;
    }

    public void setOccupiers(List<Occupier> occupiers) {
        this.occupiers = occupiers;
    }
}
