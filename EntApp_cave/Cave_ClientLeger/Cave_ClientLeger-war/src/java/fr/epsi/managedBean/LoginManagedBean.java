/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Technicien;
import fr.epsi.sessionBean.gestionConnexionSessionBeanRemote;
import fr.epsi.utils.ConstantsPages;
import java.io.IOException;
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
@ManagedBean(name = "loginManagedBean")
@SessionScoped
public class LoginManagedBean {

    private InitialContext _ic;
    @EJB
    private gestionConnexionSessionBeanRemote _gestionConnexionSessionBeanRemote;
    private Technicien _technicien;
    private Client _client;
    private String _loginClient = "";
    private String _passwordClient = "";
    private String _loginTechnicien = "";
    private String _passwordTechnicien = "";
    private boolean _showErrorClient = false;
    private boolean _showErrorTecnicien = false;

    /**
     * Creates a new instance of gestionConnexionSessionBeanRemote
     */
    public LoginManagedBean() {
        try {
            _ic = new InitialContext();
            _gestionConnexionSessionBeanRemote = (gestionConnexionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionConnexionSessionBeanRemote!fr.epsi.sessionBean.gestionConnexionSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String isClientCanConnect() {
        String result = "";
        _client = _gestionConnexionSessionBeanRemote.getClientIfCanConnect(_loginClient, _passwordClient);
        if (_client != null) {
            result = ConstantsPages.CLIENT_ACCUEIL_PAGE+"?faces-redirect=true";
            _showErrorClient = false;
        } else {
            _showErrorClient = true;
        }
        return result;
    }

    public String isTechnicienCanConnect() {
        String result = "";
        _technicien = _gestionConnexionSessionBeanRemote.getTechnicienIfCanConnect(_loginTechnicien, _passwordTechnicien);
        if (_technicien != null) {
            result = ConstantsPages.TECHNICIEN_ACCUEIL_PAGE+"?faces-redirect=true";
            _showErrorTecnicien = false;
        } else {
            _showErrorTecnicien = true;
        }
        return result;
    }

    public void logouter() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }

    /* GET/SET */
    public String getLoginClient() {
        return _loginClient;
    }

    public void setLoginClient(String loginClient) {
        _loginClient = loginClient;
    }

    public String getLoginTechnicien() {
        return _loginTechnicien;
    }

    public void setLoginTechnicien(String loginTechnicien) {
        _loginTechnicien = loginTechnicien;
    }

    public String getPasswordClient() {
        return _passwordClient;
    }

    public void setPasswordClient(String passwordClient) {
        _passwordClient = passwordClient;
    }

    public String getPasswordTechnicien() {
        return _passwordTechnicien;
    }

    public void setPasswordTechnicien(String passwordTechnicien) {
        _passwordTechnicien = passwordTechnicien;
    }

    public boolean getShowErrorClient() {
        return _showErrorClient;
    }
    
    public boolean getShowErrorTechnicien() {
        return _showErrorTecnicien;
    }

    public boolean isClientConnected() {
        return _client != null;
    }

    public boolean isTechnicienConnected() {
        return _technicien != null;
    }

    public Technicien getTechnicien() {
        return _technicien;
    }

    public void setTechnicien(Technicien _technicien) {
        this._technicien = _technicien;
    }

    public Client getClient() {
        return _client;
    }

    public void setClient(Client _client) {
        this._client = _client;
    }
}
