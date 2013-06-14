package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Contrat;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Manage Client and its contracts
 *
 * @author Anthony
 */
@Stateless
public class gestionClientSessionBean implements gestionClientSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;

    /**
     * Create a Client
     *
     * @param client
     */
    @Override
    public void createClient(Client client) {
        _em.merge(client);
    }

    /**
     * Modify a Client
     *
     * @param client
     */
    @Override
    public void modifyClient(Client client) {
        _em.merge(client);
    }

    /**
     * Delete a Client
     *
     * @param client
     */
    @Override
    public void deleteClient(Client client) {
        Client mClient = _em.find(Client.class, client.getClientId());
        _em.remove(mClient);
    }

    /**
     * Return a list of all Clients in database
     *
     * @return List<Client>
     */
    @Override
    public List<Client> getAllClients() {
        return _em.createNamedQuery("Client.findAll").getResultList();
    }

    /**
     * Return the instance of client stored in database
     *
     * @param client
     * @return Client
     */
    @Override
    public Client getClient(Client client) {
        return _em.find(Client.class, client.getClientId());
    }

    /**
     * Return the instance of client stored in database with given idClient
     *
     * @param idClient
     * @return Client
     */
    @Override
    public Client getClientById(Integer idClient) {
        Query q = _em.createNamedQuery("Client.findByClientId");
        q.setParameter("clientId", idClient);
        return (Client) q.getSingleResult();
    }

    /**
     * Return the last Contract of a client
     * @param idClient
     * @return Contrat
     */
    @Override
    public Contrat getLastContrat(Integer idClient) {
        Calendar cal = Calendar.getInstance();
        Query q = _em.createNamedQuery("Contrat.findLastByFkClientId");
        q.setParameter("fkClientId", idClient);
        q.setParameter("datenow", cal.getTime());
        return (Contrat) q.setMaxResults(1).getSingleResult();
    }

    /**
     * Update the contract need to be extended by Client
     *
     * @param contrat
     */
    @Override
    public void updateContrat(Contrat contrat) {
        _em.merge(contrat);
    }
}