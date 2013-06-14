package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Technicien;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Manage connection of clients and technicians
 *
 * @author Mikhael
 */
@Stateless
public class gestionConnexionSessionBean implements gestionConnexionSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;

    /**
     * Test client connection (check its login and password) and returns the Client instance
     *
     * @param login
     * @param password
     * @return Client
     */
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

    /**
     * Test technician connection (check its login and password) and returns the Technician instance
     *
     * @param login
     * @param password
     * @return Technicien
     */
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
