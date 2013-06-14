package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.enums.EtatIntervention;
import java.util.Calendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * Manage interventions for Clients and Technicians
 *
 * @author Quentin ecale and Anthony
 */
@Stateless
public class gestionInterventionSessionBean implements gestionInterventionSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;

    /**
     * Create an intervention
     *
     * @param intervention
     */
    @Override
    public void createIntervention(Intervention intervention) {
        _em.merge(intervention);
    }

    /**
     * Update an intervention
     *
     * @param intervention
     */
    @Override
    public void modifyIntervention(Intervention intervention) {
        _em.merge(intervention);
    }

    /**
     * Delete an intervention
     *
     * @param intervention
     */
    @Override
    public void deleteIntervention(Intervention intervention) {
        Intervention mIntervention = _em.find(Intervention.class, intervention.getInterventionId());
        _em.remove(mIntervention);
    }

    /**
     * Get all interventions for all clients
     *
     * @return List<Intervention>
     */
    @Override
    public List<Intervention> getAllIntervention() {
        return _em.createNamedQuery("Intervention.findAll").getResultList();
    }

    /**
     * Get an intervention
     *
     * @param intervention
     * @return
     */
    @Override
    public Intervention getIntervention(Intervention intervention) {
        Intervention mIntervention = _em.find(Intervention.class, intervention.getInterventionId());
        return mIntervention;
    }

    /**
     * Get an intervention with given id
     *
     * @param idIntervention
     * @return Intervention
     */
    @Override
    public Intervention getInterventionById(Integer idIntervention) {
        Query q = _em.createNamedQuery("Intervention.findByInterventionId");
        q.setParameter("interventionId", idIntervention);
        return (Intervention) q.getSingleResult();
    }

    /**
     * Get an intervention with given client
     *
     * @param clientId
     * @return
     */
    @Override
    public List<Intervention> getLstInterventionClient(Integer clientId) {
        Query q = _em.createNamedQuery("Intervention.findByFkClientId");
        q.setParameter("fkClientId", clientId);
        return q.getResultList();
    }

    /**
     * Get listIntervention with given intervention
     *
     * @param interv
     * @return
     */
    @Override
    public List<ListePiece> getListPieceIntervention(Intervention interv) {
        Query q = _em.createNamedQuery("ListePiece.findByInterventionId");
        q.setParameter("interventionId", interv.getInterventionId());

        return q.getResultList();
    }

    /**
     * Add a "piece" for an intervention (listPiece)
     *
     * @param listPiece
     */
    @Override
    public void addPieceIntervention(ListePiece listPiece) {
        _em.merge(listPiece);
    }

    /**
     * Get list of Intervention for a given technicien
     *
     * @param idTechnicien
     * @return List<Intervention>
     */
    @Override
    public List<Intervention> getListInterventionTechnicien(int idTechnicien) {
        Query q = _em.createNamedQuery("Intervention.findByFkTechnicienId");
        q.setParameter("fkTechnicienId", idTechnicien);

        return q.getResultList();
    }

    /**
     * Add a Piece in quantity given to given Intervention
     *
     * @param idPiece
     * @param idIntervention
     * @param qteToAdd
     */
    @Override
    public void addPieceToIntervention(int idPiece, int idIntervention, int qteToAdd) {
        ListePiece listePiece = new ListePiece(idPiece, idIntervention, qteToAdd);
        _em.merge(listePiece);
    }

    /**
     * give the "liste Piece" matching for idPiece and idIntervention
     *
     * @param idPiece
     * @param idIntervention
     * @return
     */
    @Override
    public List<ListePiece> existListePiece(int idPiece, int idIntervention) {
        Query q = _em.createNamedQuery("ListePiece.findByInterventionIdAndPieceId");
        q.setParameter("interventionId", idIntervention);
        q.setParameter("pieceId", idPiece);

        return q.getResultList();
    }

    /**
     * Add a Piece in quantity given to given Intervention which already exist
     *
     * @param idPiece
     * @param idIntervention
     * @param qteToAdd
     */
    @Override
    public void addPieceToInterventionAlreadyExist(int idPiece, int idIntervention, int qteToAdd) {
        Query q = _em.createNamedQuery("ListePiece.findByInterventionIdAndPieceId");
        q.setParameter("interventionId", idIntervention);
        q.setParameter("pieceId", idPiece);

        ListePiece listPiece = (ListePiece) q.getResultList().get(0);
        listPiece.setNombre(listPiece.getNombre() + qteToAdd);

        _em.merge(listPiece);
    }

    /**
     * Get the list of interventions not ended for given Client
     *
     * @param idClient
     * @return List<Intervention>
     */
    @Override
    public List<Intervention> getListInterventionClientNotEnded(int idClient) {
        Query q = _em.createNamedQuery("Intervention.findIntervNotEndedByFkClientId");
        q.setParameter("fkClientId", idClient);
        q.setParameter("etat", EtatIntervention.TERMINEE.getEnumEtatIntervention());
        return q.getResultList();
    }

    /**
     * Get the list of interventions ended matching for idClient
     *
     * @param idClient
     * @return List<Intervention>
     */
    @Override
    public List<Intervention> getListInterventionClientEnded(int idClient) {
        Query q = _em.createNamedQuery("Intervention.findIntervEndedByFkClientId");
        q.setParameter("fkClientId", idClient);
        q.setParameter("etat", EtatIntervention.TERMINEE.getEnumEtatIntervention());
        return q.getResultList();
    }

    /**
     * Get the list of interventions not ended matching for idTechnicien
     *
     * @param idTechnicien
     * @return List<Intervention>
     */
    @Override
    public List<Intervention> getListInterventionNonFinishedByTech(int idTechnicien) {
        Query q = _em.createNamedQuery("Intervention.findIntervNotEndedByFkTechId");
        q.setParameter("fkTechnicienId", idTechnicien);
        q.setParameter("etat", EtatIntervention.TERMINEE.getEnumEtatIntervention());
        return q.getResultList();
    }

    /**
     * Get the list of interventions that the technicien idTechnicien has to
     * make today
     *
     * @param idTechnicien
     * @return List<Intervention>
     */
    @Override
    public List<Intervention> getListInterventionTechnicienToday(int idTechnicien) {
        Calendar cal = Calendar.getInstance();
        Query q = _em.createNamedQuery("Intervention.findByTechnicienToday");
        q.setParameter("fkTechnicienId", idTechnicien);
        q.setParameter("date", cal.getTime(), TemporalType.DATE);
        q.setParameter("etat", EtatIntervention.PLANIFIE.getEnumEtatIntervention());
        return q.getResultList();
    }

    /**
     *
     * @param idClient
     * @return List<Intervention>
     */
    @Override
    public List<Intervention> getListInterventionClientToday(int idClient) {
        Calendar cal = Calendar.getInstance();
        Query q = _em.createNamedQuery("Intervention.findByClientToday");
        q.setParameter("fkClientId", idClient);
        q.setParameter("date", cal.getTime(), TemporalType.DATE);
        q.setParameter("etat", EtatIntervention.PLANIFIE.getEnumEtatIntervention());
        return q.getResultList();
    }
}
