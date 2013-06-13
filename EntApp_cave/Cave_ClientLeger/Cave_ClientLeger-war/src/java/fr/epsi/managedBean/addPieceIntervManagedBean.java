/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.cave.ejbentity.Piece;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import fr.epsi.sessionBean.gestionPieceSessionBeanRemote;
import fr.epsi.utils.ConstantsPages;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Quentin ecale
 */
@ManagedBean(name = "addPieceIntervManagedBean")
@SessionScoped
public class addPieceIntervManagedBean {

    private InitialContext _ic;
    private Map<String, Integer> _mapAllPiece;
    private String _idPieceToAdd;
    private int _qteToAdd;
    private int _intervId;
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean;
    @EJB
    private gestionPieceSessionBeanRemote _gestPieceBean;
    
    /**
     * Creates a new instance of addPieceIntervManagedBean
     */
    public addPieceIntervManagedBean() {
        try {
            _ic = new InitialContext();
            _gestIntervBean = (gestionInterventionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBean!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
            _gestPieceBean = (gestionPieceSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionPieceSessionBean!fr.epsi.sessionBean.gestionPieceSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     /**
     * fill the list with all the Piece in DB and their name to be displayed in
     * selectItems
     *
     * @return the page to add a piece to intervention
     */
    public String voirAjoutPiece() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();        
        _intervId = Integer.parseInt(params.get("intervId"));
        
        List<Piece> listAllPiece = _gestPieceBean.getAllPiece();
        _mapAllPiece = new TreeMap<String, Integer>();


        for (Piece p : listAllPiece) {
            _mapAllPiece.put(p.getNom(), p.getPieceId());
        }

        return ConstantsPages.TECHNICIEN_AJOUT_PIECE_INTERV_PAGE;
    }

    /**
     * Add piece to intervention if enough in stock and remove the quantity of
     * the stock
     *
     * @return path of the page to be displayed
     */
    public String ajoutePieceIntervention() {

        String result = ConstantsPages.TECHNICIEN_AJOUT_PIECE_INTERV_PAGE;
        //verifier stock
        Piece p = _gestPieceBean.getPieceById(Integer.parseInt(_idPieceToAdd));
        if (_qteToAdd > 0) {
            if (p.getQteStock() >= _qteToAdd) {
                //decrementer stock
                _gestPieceBean.decrementeStock(Integer.parseInt(_idPieceToAdd), _qteToAdd);
                //approvisionner intervention
                //si piece n'existe pas dans intervention alors l'ajouter simplement, sinon ajouter aux quantités déjà existantes
                List<ListePiece> existPiece = _gestIntervBean.existListePiece(Integer.parseInt(_idPieceToAdd), _intervId);
                if (existPiece.isEmpty()) {
                    _gestIntervBean.addPieceToIntervention(Integer.parseInt(_idPieceToAdd), _intervId, _qteToAdd);
                } else {
                    _gestIntervBean.addPieceToInterventionAlreadyExist(Integer.parseInt(_idPieceToAdd), _intervId, _qteToAdd);
                }

                result = ConstantsPages.TECHNICIEN_AVANCEMENT_PAGE;
            }
        }

        return result;
    }

    public String getNomPieceByID(int idPiece) {
        return _gestPieceBean.getPieceById(idPiece).getNom();
    }

    public Map<String, Integer> getMapAllPiece() {
        return _mapAllPiece;
    }

    public void setMapAllPiece(Map<String, Integer> _mapAllPiece) {
        this._mapAllPiece = _mapAllPiece;
    }

    public String getIdPieceToAdd() {
        return _idPieceToAdd;
    }

    public void setIdPieceToAdd(String _idPieceToAdd) {
        this._idPieceToAdd = _idPieceToAdd;
    }

    public int getQteToAdd() {
        return _qteToAdd;
    }

    public void setQteToAdd(int _qteToAdd) {
        this._qteToAdd = _qteToAdd;
    }

    public int getIntervId() {
        return _intervId;
    }

    public void setIntervId(int _intervId) {
        this._intervId = _intervId;
    }

    public gestionInterventionSessionBeanRemote getGestIntervBean() {
        return _gestIntervBean;
    }

    public void setGestIntervBean(gestionInterventionSessionBeanRemote _gestIntervBean) {
        this._gestIntervBean = _gestIntervBean;
    }

    public gestionPieceSessionBeanRemote getGestPieceBean() {
        return _gestPieceBean;
    }

    public void setGestPieceBean(gestionPieceSessionBeanRemote _gestPieceBean) {
        this._gestPieceBean = _gestPieceBean;
    }    
    
}
