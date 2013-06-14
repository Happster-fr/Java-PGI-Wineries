/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Technicien;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Mikhael
 */
@Stateless
public class gestionTechnicienSessionBean implements gestionTechnicienSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;

    /**
     * Get a list of techniciens available for date
     *
     * @param date
     * @return List<Technicien>
     */
    @Override
    public List<Technicien> getTechncienDispo(Date date) {
        String sQuerry = "SELECT t FROM Technicien t WHERE t.technicienId NOT IN (SELECT i.fkTechnicienId FROM Intervention i WHERE i.date = :dateIntervention)";
        Query q = _em.createQuery(sQuerry);
        q.setParameter("dateIntervention", date);
        return (List<Technicien>) q.getResultList();
    }

    /**
     * Get a list of techniciens available for date specialists of specialite
     *
     * @param date
     * @param specialite
     * @return List<Technicien>
     */
    @Override
    public List<Technicien> getTechncienDispo(Date date, String specialite) {
        String sQuerry = "SELECT t FROM Technicien t WHERE t.specialite = :specialite AND t.technicienId NOT IN (SELECT i.fkTechnicienId FROM Intervention i WHERE i.date = :dateIntervention)";
        Query q = _em.createQuery(sQuerry);
        q.setParameter("dateIntervention", date);
        q.setParameter("specialite", specialite);
        return (List<Technicien>) q.getResultList();
    }
}
