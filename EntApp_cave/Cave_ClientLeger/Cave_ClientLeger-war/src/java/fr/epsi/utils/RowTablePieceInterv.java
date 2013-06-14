/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

/**
 *
 * @author Quentin ecale
 */
public class RowTablePieceInterv {
    private String _nomPiece;
    private String _qteUtilise;
    
    public RowTablePieceInterv(String nomP, String qte)
    {
        this._nomPiece = nomP;
        this._qteUtilise = qte;
    }

    public String getNomPiece() {
        return _nomPiece;
    }

    public void setNomPiece(String _nomPiece) {
        this._nomPiece = _nomPiece;
    }

    public String getQteUtilise() {
        return _qteUtilise;
    }

    public void setQteUtilise(String _qteUtilise) {
        this._qteUtilise = _qteUtilise;
    }
        
}
