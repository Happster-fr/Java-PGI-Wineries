/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    gestionInterventionSessionBeanRemote _gestionInterventionBean;
    private String dateInterventionPlanifiee;
    private boolean showErrorAddInterventionPlanifiee = false;
    private boolean showMessageAddInterventionPlanifiee = false;
    
    public gestClientInterventionManagesBean() {
        try {
            _ic = new InitialContext();
            _gestionInterventionBean = (gestionInterventionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBeanRemote!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addInterventionPlanifiee() {
        Intervention intervention = new Intervention();
        intervention.setDate(null);
        intervention.setEtat("");
        intervention.setNature(""); //préventive
        _gestionInterventionBean.createIntervention(null);
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
