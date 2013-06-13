/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.sessionBean;

import fr.epsi.cave.ejbentity.Piece;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Quentin ecale
 */
@Remote
public interface gestionPieceSessionBeanRemote {
    void createPiece(Piece p);

    void modifyPiece(Piece p);
    
    void reserveQtePiece(Piece p, int qte);

    List<Piece> getAllPiece();

    Piece getPieceById(int pId);  
    
    void decrementeStock(int pId, int qte);
}
