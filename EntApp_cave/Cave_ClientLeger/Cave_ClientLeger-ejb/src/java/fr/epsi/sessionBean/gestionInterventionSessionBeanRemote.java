/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Quentin ecale
 */
@Remote
public interface gestionInterventionSessionBeanRemote {

    void createIntervention(Intervention intervention);

    void modifyIntervention(Intervention intervention);

    void deleteIntervention(Intervention intervention);

    List<Intervention> getAllIntervention();

    Intervention getIntervention(Intervention intervention);

    Intervention getInterventionById(Integer idIntervention);

    List<Intervention> getLstInterventionClient(Integer clientId);

    List<ListePiece> getListPieceIntervention(Intervention interv);

    List<Intervention> getListInterventionTechnicien(int idTechnicien);

    List<Intervention> getListInterventionClientNotEnded(int idClient);

    void addPieceIntervention(ListePiece listPiece);

    void addPieceToIntervention(int idPiece, int idIntervention, int qteToAdd);

    List<ListePiece> existListePiece(int idPiece, int idIntervention);

    void addPieceToInterventionAlreadyExist(int idPiece, int idIntervention, int qteToAdd);

    List<Intervention> getListInterventionNonFinishedByTech(int idTechnicien);
    
    List<Intervention> getListInterventionToday(int idTechnicien);
}
