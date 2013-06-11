/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Technicien;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mikhael
 */
@Stateless
public class gestionConnexionSessionBean implements gestionConnexionSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;

    @Override
    public Client getClientIfCanConnect(String login, String password) {
        Query q = _em.createNamedQuery("Client.findByLoginPassword");
        q.setParameter("login", login);
        q.setParameter("password", password);
        try {
            return (Client) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Technicien getTechnicienIfCanConnect(String login, String password) {
        Query q = _em.createNamedQuery("Technicien.findByLoginPassword");
        q.setParameter("login", login);
        q.setParameter("password", password);
        try {
            return (Technicien) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
