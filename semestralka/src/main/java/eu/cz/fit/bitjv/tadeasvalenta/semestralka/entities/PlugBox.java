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
public class PlugBox {
    private List<Plug> plugs;

    public List<Plug> getArticles() {
        return plugs;
    }

    public void setArticles(List<Plug> plugs) {
        this.plugs = plugs;
    }
}