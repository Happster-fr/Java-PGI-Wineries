/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.Technicien;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import fr.epsi.sessionBean.gestionTechnicienSessionBeanRemote;
import fr.epsi.utils.DateUtils;
import fr.epsi.utils.EnumEtatIntervention;
import fr.epsi.utils.EnumNatureIntervention;
import fr.epsi.utils.EnumSpecialiteTechnicien;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Mikhael
 */
@ManagedBean(name = "clientInterventionManagesBean")
@RequestScoped
public class gestClientInterventionManagesBean {

    private InitialContext _ic;
    @EJB
    gestionTechnicienSessionBeanRemote _gestionTechnicienSessionBean;
    @EJB
    gestionInterventionSessionBeanRemote _gestionInterventionBean;
    private String dateIntervention;
    private String typeInterventionCurative;
    private boolean showError = false;
    private boolean showMessage = false;
    private Client _client;

    public gestClientInterventionManagesBean() {
        try {
            _ic = new InitialContext();
            _gestionTechnicienSessionBean = (gestionTechnicienSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionTechnicienSessionBeanRemote!fr.epsi.sessionBean.gestionTechnicienSessionBeanRemote");
            _gestionInterventionBean = (gestionInterventionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBeanRemote!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Get current Client stored in Session
     */
    private void getCurrentClient() {
        if (_client == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Application app = context.getApplication();
            LoginManagedBean loginManagedBean = (LoginManagedBean) app.evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
            _client = loginManagedBean.getClient();
        }
    }

    public void addInterventionPlanifiee() {
        getCurrentClient();
        Date dateIntervPlanifiee = DateUtils.stringToDate(dateIntervention, DateUtils.FORMAT_DDMMYYYY);
        List<Technicien> listTechnicien = _gestionTechnicienSessionBean.getTechncienDispo(dateIntervPlanifiee);

        if (listTechnicien.size() > 0 && _client != null) {
            Technicien technicien = listTechnicien.get(0);

            Intervention intervention = new Intervention(EnumEtatIntervention.PLANIFIE.getEnumEtatIntervention(), EnumNatureIntervention.PREVENTIVE.getNatureIntervention(), EnumNatureIntervention.PREVENTIVE.getNatureIntervention(), dateIntervPlanifiee);
            intervention.setFkClientId(_client.getClientId());
            intervention.setFkTechnicienId(technicien.getTechnicienId());

            _gestionInterventionBean.createIntervention(intervention);
            showMessage = true;
            showError = false;
        } else {
            showMessage = false;
            showError = true;
        }
    }

    public void addInterventionCurative() {
        getCurrentClient();
        Date dateIntervCurative = DateUtils.stringToDate(dateIntervention, DateUtils.FORMAT_DDMMYYYY);
        List<Technicien> listTechnicien = _gestionTechnicienSessionBean.getTechncienDispo(dateIntervCurative, typeInterventionCurative);

        if (listTechnicien.size() > 0 && _client != null) {
            Technicien technicien = listTechnicien.get(0);

            Intervention intervention = new Intervention(EnumEtatIntervention.PLANIFIE.getEnumEtatIntervention(), EnumNatureIntervention.CURATIVE.getNatureIntervention(), typeInterventionCurative, dateIntervCurative);
            intervention.setFkClientId(_client.getClientId());
            intervention.setFkTechnicienId(technicien.getTechnicienId());

            _gestionInterventionBean.createIntervention(intervention);
            showMessage = true;
            showError = false;
        } else {
            showMessage = false;
            showError = true;
        }
    }

    /* GET/SET */
    public String getDateIntervention() {
        return dateIntervention;
    }

    public void setdateIntervention(String dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public boolean getShowError() {
        return showError;
    }

    public boolean getShowMessage() {
        return showMessage;
    }

    public String getTypeInterventionCurative() {
        return typeInterventionCurative;
    }

    public void setTypeInterventionCurative(String typeInterventionCurative) {
        this.typeInterventionCurative = typeInterventionCurative;
    }

    public List<String> getListTypeIntervention() {
        List<String> typesIntervention = new ArrayList<String>();
        typesIntervention.add(EnumSpecialiteTechnicien.INFORMATIQUE.getTypeIntervention());
        typesIntervention.add(EnumSpecialiteTechnicien.MECANIQUE.getTypeIntervention());
        typesIntervention.add(EnumSpecialiteTechnicien.ELECTROMECANIQUE.getTypeIntervention());
        return typesIntervention;
    }
}
