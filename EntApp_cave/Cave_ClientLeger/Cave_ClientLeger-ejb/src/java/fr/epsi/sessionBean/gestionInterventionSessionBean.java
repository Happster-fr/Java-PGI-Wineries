/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Quentin ecale
 */
@Stateless
public class gestionInterventionSessionBean implements gestionInterventionSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;
    
    @Override
    public void createIntervention(Intervention intervention) {
        _em.merge(intervention);
    }

    @Override
    public void modifyIntervention(Intervention intervention) {
        _em.merge(intervention);
    }

    @Override
    public void deleteIntervention(Intervention intervention) {
        Intervention mIntervention = _em.find(Intervention.class, intervention.getInterventionId());
        _em.remove(mIntervention);
    }

    @Override
    public List<Intervention> getAllIntervention() {
        return _em.createNamedQuery("Intervention.findAll").getResultList();
    }

    @Override
    public Intervention getIntervention(Intervention intervention) {
        Intervention mIntervention = _em.find(Intervention.class, intervention.getInterventionId());
        return mIntervention;
    }

    @Override
    public Intervention getInterventionById(Integer idIntervention) {
        Query q = _em.createNamedQuery("Intervention.findByInterventionId");
        q.setParameter("interventionId", idIntervention);
        return (Intervention) q.getSingleResult();
    }

    @Override
    public List<Intervention> getLstInterventionClient(Integer clientId) {
        Query q = _em.createNamedQuery("Intervention.findByFkClientId");
        q.setParameter("fkClientId", clientId);
        return q.getResultList();
    }

    @Override
    public List<ListePiece> getListPieceIntervention(Intervention interv) {
        Query q = _em.createNamedQuery("ListePiece.findByInterventionId");
        q.setParameter("interventionId", interv.getInterventionId());
        
        return q.getResultList();
    }

    @Override
    public void addPieceIntervention(ListePiece listPiece) {
        _em.merge(listPiece);
    }

    @Override
    public List<Intervention> getListInterventionTechnicien(int idTechnicien) {
        Query q = _em.createNamedQuery("Intervention.findByFkTechnicienId");
        q.setParameter("fkTechnicienId",idTechnicien);
        
        return q.getResultList();
    }

}
