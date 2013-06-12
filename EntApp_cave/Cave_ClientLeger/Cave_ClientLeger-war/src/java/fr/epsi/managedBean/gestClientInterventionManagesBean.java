/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.sessionBean.gestionClientSessionBeanRemote;
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
    
    public gestClientInterventionManagesBean() {
        try {
            _ic = new InitialContext();
            _gestionInterventionBean = (gestionInterventionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBeanRemote!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
