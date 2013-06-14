package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.Technicien;
import fr.epsi.sessionBean.gestionClientSessionBeanRemote;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/*
 * Use to display Google maps
 * @author Anthony
 */
/**
 *
 * @author Antho
 */
@ManagedBean(name = "mapBean")
@RequestScoped
public class AccueilManagedBean implements Serializable {

    private MapModel simpleModel;
    private Client client;
    private Technicien technicien;
    private InitialContext ic;
    private List<Intervention> listToday;
    private gestionInterventionSessionBeanRemote gstInterventionSessionBeanRemote;
    private gestionClientSessionBeanRemote gstClientSessionBeanRemote;

    /**
     * Set the current client
     *
     * @param client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     *
     */
    public AccueilManagedBean() {
        try {
            ic = new InitialContext();
            gstInterventionSessionBeanRemote = (gestionInterventionSessionBeanRemote) ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBean!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
            gstClientSessionBeanRemote = (gestionClientSessionBeanRemote) ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionClientSessionBean!fr.epsi.sessionBean.gestionClientSessionBeanRemote");
            simpleModel = new DefaultMapModel();
        } catch (NamingException ex) {
            Logger.getLogger(AccueilManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Get the Current Client name
     *
     * @return Stringjavadoc
     */
    public String getCurrentClientName() {
        getCurrentUser();
        return client.getNom();
    }

    public String getNumuberOfInterventionsToday() {
        String returned = "";
        int number = gstInterventionSessionBeanRemote.getListInterventionClientToday(client.getClientId()).size();
        if (number > 1) {
            returned = "Vous avez " + number + " interventions prévues aujourd'hui !";
        } else if (number == 1) {
            returned = "Vous avez " + number + " intervention prévue aujourd'hui !";
        } else {
            returned = "Vous n'avez pas d'interventions prévues aujourd'hui.";
        }
        return returned;
    }

    private void getCurrentUser() {
        if (client == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Application app = context.getApplication();
            LoginManagedBean loginManagedBean = (LoginManagedBean) app.evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
            client = loginManagedBean.getClient();
        }
    }

    private void getCurrentTech() {
        if (technicien == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Application app = context.getApplication();
            LoginManagedBean loginManagedBean = (LoginManagedBean) app.evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
            technicien = loginManagedBean.getTechnicien();
        }
    }

    /**
     * Get a mapmodel for current client
     *
     * @return
     */
    public MapModel getCurrentClientMarker() {
        getCurrentUser();
        if (client != null) {
            float lat = client.getLatitude();
            float longi = client.getLongitude();
            LatLng coordClient = new LatLng(lat, longi);
            simpleModel.addOverlay(new Marker(coordClient, client.getNom()));
        }
        return simpleModel;
    }

    /**
     * Get a mapmodel with Marker of clients who need intervention today
     *
     * @return
     */
    public MapModel getTodayClientsMarker() {
        getCurrentTech();
        float lat, longi;
        Client cli;
        if (technicien != null) {
            listToday = gstInterventionSessionBeanRemote.getListInterventionTechnicienToday(technicien.getTechnicienId());
            for (Intervention i : listToday) {
                cli = gstClientSessionBeanRemote.getClientById(i.getFkClientId());
                lat = cli.getLatitude();
                longi = cli.getLongitude();
                LatLng coordClient = new LatLng(lat, longi);
                simpleModel.addOverlay(new Marker(coordClient, cli.getNom()));
            }
        }
        return simpleModel;
    }

    /**
     * Get a DataModel with Marker of clients who need intervention today
     *
     * @return
     */
    public DataModel getTodayClientsDm() {
        ListDataModel ldm = new ListDataModel();
        ldm.setWrappedData(listToday);
        return ldm;
    }

    /* GET/SET */
    /**
     *
     * @return
     */
    public MapModel getSimpleModel() {
        return simpleModel;
    }

    /**
     * Get list of interventions to make today
     *
     * @return List<Intervention>
     */
    public List<Intervention> getListToday() {
        return listToday;
    }

    /**
     * Set list of interventions to make today
     *
     * @param listToday
     */
    public void setListToday(List<Intervention> listToday) {
        this.listToday = listToday;
    }

    /**
     * Get the current client
     *
     * @return Client
     */
    public Client getClient() {
        return client;
    }
}
