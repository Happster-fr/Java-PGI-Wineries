package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Client;
import fr.epsi.cave.ejbentity.Contrat;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Anthony
 */
@Remote
public interface gestionClientSessionBeanRemote {

    void createClient(Client client);

    void modifyClient(Client client);

    void deleteClient(Client client);

    List<Client> getAllClients();

    Client getClient(Client client);

    Client getClientById(Integer idClient);

    Contrat getLastContrat(Integer idClient);

    void updateContrat(Contrat contrat);
}
