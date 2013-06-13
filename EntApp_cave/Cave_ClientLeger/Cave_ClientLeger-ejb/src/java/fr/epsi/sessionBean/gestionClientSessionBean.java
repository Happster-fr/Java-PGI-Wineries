/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Anthony
 */
@Stateless
public class gestionClientSessionBean implements gestionClientSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;

    @Override
    public void createClient(Client client) {
        _em.merge(client);
    }

    @Override
    public void modifyClient(Client client) {
        _em.merge(client);
    }

    @Override
    public void deleteClient(Client client) {
        Client mClient = _em.find(Client.class, client.getClientId());
        _em.remove(mClient);
    }

    @Override
    public List<Client> getAllClients() {
        return _em.createNamedQuery("Client.findAll").getResultList();
    }

    @Override
    public Client getClient(Client client) {
        return _em.find(Client.class, client.getClientId());
    }

    @Override
    public Client getClientById(Integer idClient) {
        Query q = _em.createNamedQuery("Client.findByClientId");
        q.setParameter("clientId", idClient);
        return (Client) q.getSingleResult();
    }

    @Override
    public Contrat getLastContrat(Integer idClient) {
        Calendar cal = Calendar.getInstance();
        Query q = _em.createNamedQuery("Contrat.findLastByFkClientId");
        q.setParameter("fkClientId", idClient);
        q.setParameter("datenow", cal.getTime());
        return (Contrat) q.setMaxResults(1).getSingleResult();
    }

    @Override
    public void modifyContrat(Contrat contrat) {
        _em.merge(contrat);
    }

    @Override
    public void updateContrat(Contrat contrat) {
        //Contrat newContrat = new Contrat(contrat);
        _em.merge(contrat);
        //return newContrat;
    }
}