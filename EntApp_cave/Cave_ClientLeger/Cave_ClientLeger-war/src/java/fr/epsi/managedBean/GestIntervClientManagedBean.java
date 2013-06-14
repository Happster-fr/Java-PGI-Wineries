/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.sessionBean.gestionClientSessionBeanRemote;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import fr.epsi.sessionBean.gestionPieceSessionBeanRemote;
import fr.epsi.utils.ConstantsPages;
import fr.epsi.utils.FactureCreator;
import java.util.Map;
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
 * @author Anthony
 */
@ManagedBean(name = "gestIntervClientManagedBean")
@RequestScoped
public class GestIntervClientManagedBean {
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean;
    @EJB
    private gestionPieceSessionBeanRemote _gestionPieceSessionBeanRemote;
    @EJB
    private gestionClientSessionBeanRemote _gestionClientSessionBeanRemote;
    private DataModel _dmInterventionsNotEnded;
    private String _htmlFacture;

    public GestIntervClientManagedBean() {
        try {
            InitialContext ic = new InitialContext();
            _gestIntervBean = (gestionInterventionSessionBeanRemote) ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBean!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
            _gestionPieceSessionBeanRemote = (gestionPieceSessionBeanRemote) ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionPieceSessionBean!fr.epsi.sessionBean.gestionPieceSessionBeanRemote");
            _gestionClientSessionBeanRemote = (gestionClientSessionBeanRemote) ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionClientSessionBeanRemote!fr.epsi.sessionBean.gestionClientSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Business stuff */
    /**
     * Get interventions for current client with state "En attente"
     *
     * @return DataModel
     */
    public DataModel getInterventionCurrentClientAttente() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean loginManagedBean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
        int clientId = loginManagedBean.getClient().getClientId();

        if (_dmInterventionsNotEnded == null) {
            _dmInterventionsNotEnded = new ListDataModel();
            _dmInterventionsNotEnded.setWrappedData(_gestIntervBean.getListInterventionClientNotEnded(clientId));
        }

        return _dmInterventionsNotEnded;
    }

    /**
     * Get interventions for current client with state "Terminee"
     *
     * @return DataModel
     */
    public DataModel getInterventionCurrentClientPassees() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean loginManagedBean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
        int clientId = loginManagedBean.getClient().getClientId();

        if (_dmInterventionsNotEnded == null) {
            _dmInterventionsNotEnded = new ListDataModel();
            _dmInterventionsNotEnded.setWrappedData(_gestIntervBean.getListInterventionClientEnded(clientId));
        }

        return _dmInterventionsNotEnded;
    }

    public String imprimerIntervention() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean loginManagedBean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
        Client client = loginManagedBean.getClient();
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        if (!params.get("intervToPrint").isEmpty()) {
            Intervention intervention = _gestIntervBean.getInterventionById(Integer.parseInt(params.get("intervToPrint")));
            FactureCreator facturerCreator = new FactureCreator(client, intervention, _gestionClientSessionBeanRemote.getLastContrat(client.getClientId()), _gestionPieceSessionBeanRemote);
            _htmlFacture = facturerCreator.HtmlFacture();
        }
        return ConstantsPages.CLIENT_INTERVENTION_TO_PRINT;
    }
    
    /* GET/SET */
    
    public String getHtmlFacture() {
        return _htmlFacture;
    }
}
