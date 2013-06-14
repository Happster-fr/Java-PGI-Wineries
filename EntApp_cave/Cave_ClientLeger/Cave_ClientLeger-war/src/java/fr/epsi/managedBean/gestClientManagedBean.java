package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Contrat;
import fr.epsi.enums.TypeContrat;
import fr.epsi.sessionBean.gestionClientSessionBeanRemote;
import fr.epsi.utilities.ClientUtilities;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Anthony
 */
@ManagedBean(name = "clientManagedBean")
@SessionScoped
public class gestClientManagedBean {

    private InitialContext _ic;
    @EJB
    gestionClientSessionBeanRemote _gestClientBean;
    private Client _client;
    private Contrat _contrat;
    private LoginManagedBean _loginManagedBean;
    private boolean updateOk = false;
    private boolean updateNok = false;

    public gestClientManagedBean() {
        try {
            _ic = new InitialContext();
            _gestClientBean = (gestionClientSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionClientSessionBeanRemote!fr.epsi.sessionBean.gestionClientSessionBeanRemote");
            getCurrentClient();
            getCurrentContract();
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Business stuff */
    /**
     * Get current Client stored in Session
     */
    private void getCurrentClient() {
        if (_client == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Application app = context.getApplication();
            _loginManagedBean = (LoginManagedBean) app.evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);
            _client = _loginManagedBean.getClient();
        }
    }

    private void getCurrentContract() {
        getCurrentClient();
        if (_contrat == null) {
            _contrat = _gestClientBean.getLastContrat(_client.getClientId());
        }
    }

    /**
     * Get list of all TypeContrat to populate views
     *
     * @return List<String>
     */
    public List<String> getListTypeContratStr() {
        List<String> types = new ArrayList<String>();
        types.add(TypeContrat.ANNUEL.getTypeContratInsert());
        types.add(TypeContrat.MENSUEL.getTypeContratInsert());
        types.add(TypeContrat.HEBDOMADAIRE.getTypeContratInsert());
        return types;
    }

    /**
     * Used by AJAX call to change automatically "date debut" and "date fin"
     * attributes
     *
     * @param e
     */
    public void changeDates(ValueChangeEvent e) {
        Calendar cal = Calendar.getInstance();
        _contrat.setDateDebut(cal.getTime());
        _contrat.setDateFin(ClientUtilities.changeDate(_contrat.getDateDebut(), _contrat.getType()));
    }

    /**
     * Submit form and show success or failed message
     */
    public void submitForm() {;
        _gestClientBean.updateContrat(_contrat);
        updateOk = true;
    }

    /* GET/SET */
    public Client getClient() {
        getCurrentClient();
        return _client;
    }

    public void setClient(Client _client) {
        this._client = _client;
    }

    public Contrat getContrat() {
        getCurrentContract();
        return _contrat;
    }

    public void setContrat(Contrat _contrat) {
        this._contrat = _contrat;
    }

    public boolean isInsertOk() {
        return updateOk;
    }

    public void setInsertOk(boolean insertOk) {
        this.updateOk = insertOk;
    }

    public boolean isInsertNok() {
        return updateNok;
    }

    public void setInsertNok(boolean insertNok) {
        this.updateNok = insertNok;
    }
}