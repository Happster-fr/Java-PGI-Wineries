/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Antho
 */
@ManagedBean(name = "gestIntervClientManagedBean")
@RequestScoped
public class GestIntervClientManagedBean {

    private InitialContext _ic;
    private LoginManagedBean _loginManagedBean;
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean;
    private DataModel _dmInterventionsNotEnded;
   

    public GestIntervClientManagedBean() {
        try {
            _ic = new InitialContext();
            _gestIntervBean = (gestionInterventionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBean!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DataModel getInterventionCurrentClientAttente() {
        FacesContext context = FacesContext.getCurrentInstance();
        _loginManagedBean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
        int clientId = _loginManagedBean.getClient().getClientId();
        
        if (_dmInterventionsNotEnded == null) {
            _dmInterventionsNotEnded = new ListDataModel();
            _dmInterventionsNotEnded.setWrappedData(_gestIntervBean.getListInterventionClientNotEnded(clientId));
        }

        return _dmInterventionsNotEnded;
    }
}
