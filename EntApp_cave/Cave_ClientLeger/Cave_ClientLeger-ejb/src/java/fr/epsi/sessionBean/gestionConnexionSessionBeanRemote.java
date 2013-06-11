/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Technicien;

/**
 *
 * @author Mikhael
 */
public interface gestionConnexionSessionBeanRemote {
    public Client getClientIfCanConnect(String login, String password);
    public Technicien getTechnicienIfCanConnect(String login, String password);
}
