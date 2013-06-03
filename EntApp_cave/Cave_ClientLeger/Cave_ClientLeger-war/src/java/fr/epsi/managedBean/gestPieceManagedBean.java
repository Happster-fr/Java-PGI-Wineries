/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Piece;
import fr.epsi.sessionBean.gestionPieceSessionBeanRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Quentin ecale
 */
@ManagedBean(name = "gestPieceManagedBean")
@SessionScoped
public class gestPieceManagedBean {

    private InitialContext _ic;
    private DataModel _listPiece;    
    @EJB
    private gestionPieceSessionBeanRemote _gestPiece;
    private Piece _piece;
    private Piece _editPiece;

    /**
     * Creates a new instance of gestPieceManagedBean
     */
    public gestPieceManagedBean() {
        try {
            _ic = new InitialContext();
            _gestPiece = (gestionPieceSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionPieceSessionBean!fr.epsi.sessionBean.gestionPieceSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void approvisionnerStock(int qte) {
        _piece.setQteStock(qte);
        _gestPiece.modifyPiece(_piece);
    }

    public DataModel getListPiece() {
        if (_listPiece == null) {
            _listPiece = new ListDataModel();
            _listPiece.setWrappedData(_gestPiece.getAllPiece());
        }

        return _listPiece;
    }        

    public String editPiece() {
        _editPiece = (Piece) _listPiece.getRowData();
        return "edit";
    }
    
    public String updatePiece() {
        _gestPiece.modifyPiece(_editPiece);
        _listPiece.setWrappedData(_gestPiece.getAllPiece());
        
        return "listPiece";
    }

    public InitialContext getIc() {
        return _ic;
    }

    public Piece getEditPiece() {
        return _editPiece;
    }

    public gestionPieceSessionBeanRemote getGestPiece() {
        return _gestPiece;
    }

    public Piece getPiece() {
        return _piece;
    }

    public void setIc(InitialContext _ic) {
        this._ic = _ic;
    }

    public void setListPiece(DataModel _listPiece) {
        this._listPiece = _listPiece;
    }

    public void setEditPiece(Piece _editPiece) {
        this._editPiece = _editPiece;
    }

    public void setGestPiece(gestionPieceSessionBeanRemote _gestPiece) {
        this._gestPiece = _gestPiece;
    }

    public void setPiece(Piece _piece) {
        this._piece = _piece;
    }        
}
