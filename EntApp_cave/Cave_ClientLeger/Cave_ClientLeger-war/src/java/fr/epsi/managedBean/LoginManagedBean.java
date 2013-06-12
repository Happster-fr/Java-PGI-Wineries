/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Technicien;
import fr.epsi.sessionBean.gestionConnexionSessionBeanRemote;
import fr.epsi.utils.ConstantsPages;
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
@ManagedBean(name = "loginManagedBean")
@SessionScoped
public class LoginManagedBean {
    private InitialContext _ic;
    @EJB
    private gestionConnexionSessionBeanRemote _gestionConnexionSessionBeanRemote;
    private Technicien _technicien;
    private Client _client;
    private String _login = "";
    private String _password = "";
    private boolean _showError = false;

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
        _client = _gestionConnexionSessionBeanRemote.getClientIfCanConnect(_login, _password);
        if (_client != null) {
            result = ConstantsPages.ACCUEIL_PAGE;
        } else {
            _showError = true;
        }
        return result;
    }

    public String isTechnicienCanConnect() {
        String result = "";
        _technicien = _gestionConnexionSessionBeanRemote.getTechnicienIfCanConnect(_login, _password);
        if (_technicien != null) {
            result = ConstantsPages.TECHNICIEN_ACCUEIL_PAGE;
        } else {
            _showError = true;
        }
        return result;
    }
    
    /* GET/SET */
    public String getLogin() {
        return _login;
    }
    
    public void setLogin(String login) {
        _login = login;
    }
    
    public String getPassword() {
        return _password;
    }
    
    public void setPassword(String password) {
        _password = password;
    }
    
    public boolean getShowError() {
        return _showError;
    }
    
    public Technicien getTechnicien() {
        return _technicien;
    }
}
