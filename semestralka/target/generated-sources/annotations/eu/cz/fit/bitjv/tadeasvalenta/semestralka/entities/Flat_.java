package eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities;

import eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities.Occupier;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities.Plug;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-02T11:32:31")
@StaticMetamodel(Flat.class)
public class Flat_ { 

    public static volatile SingularAttribute<Flat, Long> area;
    public static volatile SingularAttribute<Flat, Long> doorNumber;
    public static volatile SetAttribute<Flat, Plug> plugs;
    public static volatile SingularAttribute<Flat, Long> id;
    public static volatile SingularAttribute<Flat, Long> rent;
    public static volatile SingularAttribute<Flat, Occupier> occupier;

}