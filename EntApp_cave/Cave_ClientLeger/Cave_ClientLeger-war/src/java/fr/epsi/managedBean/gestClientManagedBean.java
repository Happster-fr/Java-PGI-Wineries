/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Contrat;
import fr.epsi.enums.TypeContrat;
import fr.epsi.sessionBean.gestionClientSessionBeanRemote;
import fr.epsi.utilities.ClientUtilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Antho
 */
@ManagedBean(name = "clientManagedBean")
@SessionScoped
public class gestClientManagedBean {

    private InitialContext _ic;
    @EJB
    gestionClientSessionBeanRemote _gestClientBean;
    private Client _client;
    private Contrat _contrat;

    public gestClientManagedBean() {
        try {
            _ic = new InitialContext();
            _gestClientBean = (gestionClientSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBean!fr.epsi.sessionBean.gestionClientSessionBeanRemote");
            /*getCurrentClient();
            getCurrentContract();*/
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getCurrentClient() {
        if (_client == null) {
            //loginManagedBean.
            _client = _gestClientBean.getClientById(1);
        }
    }

    private void getCurrentContract() {
        getCurrentClient();
        if (_contrat == null) {
            _contrat = _gestClientBean.getLastContrat(_client.getClientId());
        }
    }

    public Integer getCurrentClientContractId() {
        getCurrentContract();
        return _contrat.getContratId();
    }

    public TypeContrat getCurrentClientContractType() {
        getCurrentContract();
        return ClientUtilities.getTypeContractByString(_contrat.getType());
    }

    public String getCurrentClientContractTypeStr() {
        return getCurrentClientContractType().getTypeContratInsert();
    }

    public Date getCurrentClientContractDateDeb() {
        getCurrentContract();
        return _contrat.getDateDebut();
    }

    public Date getCurrentClientContractDateFin() {
        getCurrentContract();
        return _contrat.getDateFin();
    }

    public List<String> getListTypeContratStr() {
        List<String> types = new ArrayList<String>();
        types.add(TypeContrat.ANNUEL.getTypeContratInsert());
        types.add(TypeContrat.MENSUEL.getTypeContratInsert());
        types.add(TypeContrat.HEBDOMADAIRE.getTypeContratInsert());
        return types;
    }
    
    public void changeDates(AjaxBehaviorEvent event){
        _contrat.setDateFin(null);
        _contrat.setDateDebut(null);
    }
    
    /*public String saveContractModifications(){
        String destination = "";
        if()
    }*/
}
