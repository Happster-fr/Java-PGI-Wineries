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
import fr.epsi.utils.EnumNatureIntervention;
import fr.epsi.utils.DateUtils;
import fr.epsi.utils.EnumEtatIntervention;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Mikhael
 */
@ManagedBean(name = "clientInterventionManagesBean")
@SessionScoped
public class gestClientInterventionManagesBean {

    private InitialContext _ic;
    @EJB
    gestionTechnicienSessionBeanRemote _gestionTechnicienSessionBean;
    @EJB
    gestionInterventionSessionBeanRemote _gestionInterventionBean;
    private String dateInterventionPlanifiee;
    private boolean showErrorAddInterventionPlanifiee = false;
    private boolean showMessageAddInterventionPlanifiee = false;

    public gestClientInterventionManagesBean() {
        try {
            _ic = new InitialContext();
            _gestionTechnicienSessionBean = (gestionTechnicienSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionTechnicienSessionBeanRemote!fr.epsi.sessionBean.gestionTechnicienSessionBeanRemote");
            _gestionInterventionBean = (gestionInterventionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBeanRemote!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addInterventionPlanifiee() {
<<<<<<< HEAD
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean bean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
        Client client = bean.getClient();

=======
        /*FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean bean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);        
        Client client = bean.getClient();*/
        
>>>>>>> 476f947b81adcde1751a04a34aaed77e90ea6cdd
        Date dateIntervPlanifiee = DateUtils.stringToDate(dateInterventionPlanifiee, DateUtils.FORMAT_DDMMYYYY);
        List<Technicien> listTechnicien = _gestionTechnicienSessionBean.getTechncienDispo(dateIntervPlanifiee);

        if (listTechnicien.size() > 0) {
            Technicien technicien = listTechnicien.get(0);
<<<<<<< HEAD

            Intervention intervention = new Intervention();
            intervention.setDate(dateIntervPlanifiee);
            intervention.setEtat(EnumEtatIntervention.PLANIFIE.name());
            intervention.setNature(EnumNatureIntervention.PREVENTIVE.name());
            intervention.setType("");
            intervention.setFkClientId(client.getClientId());
            intervention.setFkTechnicienId(technicien.getTechnicienId());

            _gestionInterventionBean.createIntervention(intervention);

=======
            
            Intervention intervention = new Intervention(EnumEtatIntervention.PLANIFIE.getEnumEtatIntervention(), EnumNatureIntervention.PREVENTIVE.getNatureIntervention(), EnumNatureIntervention.PREVENTIVE.getNatureIntervention(), dateIntervPlanifiee);
            intervention.setFkClientId(1);
            intervention.setFkTechnicienId(technicien.getTechnicienId());

            _gestionInterventionBean.createIntervention(intervention);
>>>>>>> 476f947b81adcde1751a04a34aaed77e90ea6cdd
            showMessageAddInterventionPlanifiee = true;
            showErrorAddInterventionPlanifiee = false;
        } else {
            showMessageAddInterventionPlanifiee = false;
            showErrorAddInterventionPlanifiee = true;
        }
    }

    /* GET/SET */
    public String getDateInterventionPlanifiee() {
        return dateInterventionPlanifiee;
    }

    public void setdateInterventionPlanifiee(String dateInterventionPlanifiee) {
        this.dateInterventionPlanifiee = dateInterventionPlanifiee;
    }

    public boolean getShowErrorAddInterventionPlanifiee() {
        return showErrorAddInterventionPlanifiee;
    }

    public boolean getShowMessageAddInterventionPlanifiee() {
        return showMessageAddInterventionPlanifiee;
    }
}
