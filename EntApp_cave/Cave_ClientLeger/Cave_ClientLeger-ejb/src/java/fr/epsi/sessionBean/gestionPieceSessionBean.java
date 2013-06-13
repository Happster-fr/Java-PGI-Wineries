/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Piece;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Quentin ecale
 */
@Stateless
public class gestionPieceSessionBean implements gestionPieceSessionBeanRemote {

    @PersistenceContext
    private EntityManager _em;

    @Override
    public void createPiece(Piece p) {
        _em.merge(p);
    }

    @Override
    public void modifyPiece(Piece p) {
        _em.merge(p);
    }

    @Override
    public void reserveQtePiece(Piece p, int qte) {
        p.setQteStock(p.getQteStock()+qte);
        _em.merge(p);
    }

    @Override
    public List<Piece> getAllPiece() {
        return _em.createNamedQuery("Piece.findAll").getResultList();
    }

    @Override
    public Piece getPieceById(int pId) {
        Piece piece = _em.find(Piece.class, pId);
        return piece;
    }

    @Override
    public void decrementeStock(int pId, int qte) {
        Piece piece = _em.find(Piece.class, pId);
        piece.setQteStock(piece.getQteStock()-qte);
        _em.merge(piece);
    }

}
