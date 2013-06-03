package fr.epsi.cave.ejbentity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2013-06-02T11:05:06")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> statut;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, Date> dateDebContrat;
    public static volatile SingularAttribute<Client, String> contrat;
    public static volatile SingularAttribute<Client, Date> dateFinContrat;
    public static volatile SingularAttribute<Client, Integer> clientId;
    public static volatile SingularAttribute<Client, String> nom;

}