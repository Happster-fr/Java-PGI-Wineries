/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.cave.ejbentity.Piece;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import fr.epsi.sessionBean.gestionPieceSessionBeanRemote;
import fr.epsi.utils.ConstantsPages;
import fr.epsi.utils.EnumEtatIntervention;
import java.util.Arrays;
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
@ManagedBean(name = "gestIntervManagedBean")
@SessionScoped
public class gestIntervManagedBean {

    private InitialContext _ic;
    private Map<String, Integer> _mapAllPiece;
    private String _idPieceToAdd;
    private int _qteToAdd;
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean;
    @EJB
    private gestionPieceSessionBeanRemote _gestPieceBean;
    private Intervention _detailInterv;
    private List<ListePiece> _listPieceInterv;
    private Intervention _newIntervention;

    /**
     * Creates a new instance of gestIntervManagedBean
     */
    public gestIntervManagedBean() {

        try {
            _ic = new InitialContext();
            _gestIntervBean = (gestionInterventionSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionInterventionSessionBean!fr.epsi.sessionBean.gestionInterventionSessionBeanRemote");
            _gestPieceBean = (gestionPieceSessionBeanRemote) _ic.lookup("java:global/Cave_ClientLeger/Cave_ClientLeger-ejb/gestionPieceSessionBean!fr.epsi.sessionBean.gestionPieceSessionBeanRemote");
        } catch (NamingException ex) {
            Logger.getLogger(gestPieceManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * get intervention ID passed by parameters and get all the object and Piece
     * to display it in the avancement page
     *
     * @return String corresponding to the avancement page
     */
    public String voirAvancementIntervention() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        _detailInterv = _gestIntervBean.getInterventionById(Integer.parseInt(params.get("intervToSee")));
        _listPieceInterv = _gestIntervBean.getListPieceIntervention(_detailInterv);
        return ConstantsPages.TECHNICIEN_AVANCEMENT_PAGE;
    }

    /**
     * record changes done on the intervention
     *
     * @return String corresponding to the list of all intervention of the
     * technicien
     */
    public String enregistrerAvancement() {
        _gestIntervBean.modifyIntervention(_detailInterv);
        return ConstantsPages.TECHNICIEN_A_REAL_PAGE;
    }

    /**
     * fill the list with all the Piece in DB and their name to be displayed in
     * selectItems
     *
     * @return the page to add a piece to intervention
     */
    public String voirAjoutPiece() {
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
                List<ListePiece> existPiece = _gestIntervBean.existListePiece(Integer.parseInt(_idPieceToAdd), _detailInterv.getInterventionId());
                if (existPiece.isEmpty()) {
                    _gestIntervBean.addPieceToIntervention(Integer.parseInt(_idPieceToAdd), _detailInterv.getInterventionId(), _qteToAdd);
                } else {
                    _gestIntervBean.addPieceToInterventionAlreadyExist(Integer.parseInt(_idPieceToAdd), _detailInterv.getInterventionId(), _qteToAdd);
                }

                result = voirAvancementIntervention();//ConstantsPages.TECHNICIEN_AVANCEMENT_PAGE;
            }
        }

        return result;
    }

    public List<EnumEtatIntervention> getAllEtatInterv() {
        return Arrays.asList(EnumEtatIntervention.values());
    }

    public String getNomPieceByID(int idPiece) {
        return _gestPieceBean.getPieceById(idPiece).getNom();
    }

    public InitialContext getIc() {
        return _ic;
    }

    public gestionInterventionSessionBeanRemote getGestIntervBean() {
        return _gestIntervBean;
    }

    public Intervention getDetailInterv() {
        return _detailInterv;
    }

    public List<ListePiece> getListPieceInterv() {
        return _listPieceInterv;
    }

    public Map<String, Integer> getMapAllPiece() {
        return _mapAllPiece;
    }

    public Intervention getNewIntervention() {
        return _newIntervention;
    }

    public String getIdPieceToAdd() {
        return _idPieceToAdd;
    }

    public int getQteToAdd() {
        return _qteToAdd;
    }

    public void setIc(InitialContext _ic) {
        this._ic = _ic;
    }

    public void setGestIntervBean(gestionInterventionSessionBeanRemote _gestIntervBean) {
        this._gestIntervBean = _gestIntervBean;
    }

    public void setDetailInterv(Intervention _detailInterv) {
        this._detailInterv = _detailInterv;
    }

    public void setListPieceInterv(List<ListePiece> _listPieceInterv) {
        this._listPieceInterv = _listPieceInterv;
    }

    public void setNewIntervention(Intervention _newIntervention) {
        this._newIntervention = _newIntervention;
    }

    public void setMapAllPiece(Map<String, Integer> _mapAllPiece) {
        this._mapAllPiece = _mapAllPiece;
    }

    public void setIdPieceToAdd(String _idPieceToAdd) {
        this._idPieceToAdd = _idPieceToAdd;
    }

    public void setQteToAdd(int _qteToAdd) {
        this._qteToAdd = _qteToAdd;
    }
}
