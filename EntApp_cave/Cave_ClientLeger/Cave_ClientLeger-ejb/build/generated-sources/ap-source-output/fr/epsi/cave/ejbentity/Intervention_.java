package fr.epsi.cave.ejbentity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-02T11:05:06")
@StaticMetamodel(Intervention.class)
public class Intervention_ { 

    public static volatile SingularAttribute<Intervention, String> etat;
    public static volatile SingularAttribute<Intervention, Integer> fkClientId;
    public static volatile SingularAttribute<Intervention, String> nature;
    public static volatile SingularAttribute<Intervention, Date> date;
    public static volatile SingularAttribute<Intervention, String> type;
    public static volatile SingularAttribute<Intervention, Integer> fkTechnicienId;
    public static volatile SingularAttribute<Intervention, Integer> interventionId;

}