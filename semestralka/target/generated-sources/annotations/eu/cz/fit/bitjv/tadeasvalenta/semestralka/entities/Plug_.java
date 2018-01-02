package eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities;

import eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities.Flat;
import eu.cz.fit.bitjv.tadeasvalenta.semestralka.entities.PlugType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-02T11:32:31")
@StaticMetamodel(Plug.class)
public class Plug_ { 

    public static volatile SingularAttribute<Plug, String> provider;
    public static volatile SingularAttribute<Plug, Flat> flat;
    public static volatile SingularAttribute<Plug, Long> payment;
    public static volatile SingularAttribute<Plug, Long> id;
    public static volatile SingularAttribute<Plug, PlugType> type;

}