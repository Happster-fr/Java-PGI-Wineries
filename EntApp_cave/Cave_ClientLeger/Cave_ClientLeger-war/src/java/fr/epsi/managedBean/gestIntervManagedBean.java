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
import fr.epsi.utils.RowTablePieceInterv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean;
    @EJB
    private gestionPieceSessionBeanRemote _gestPieceBean;
    private Intervention _detailInterv;
    private DataModel _pieceUseInterv;
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
        if (!params.get("intervToSee").isEmpty()) {
            _detailInterv = _gestIntervBean.getInterventionById(Integer.parseInt(params.get("intervToSee")));
        }
        
        return ConstantsPages.TECHNICIEN_AVANCEMENT_PAGE+"?faces-redirect=true";
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
     * Fill DataModel to show Piece and number used for intervention
     * @return DataModel which contains Piece of intervention
     */
    public DataModel getDataPieceIntervention() {
        List<RowTablePieceInterv> listPieceInterv = new ArrayList();
        List<ListePiece> listeP = _gestIntervBean.getListPieceIntervention(_detailInterv);
        _pieceUseInterv = new ListDataModel();
        for (ListePiece lp : listeP) {
            RowTablePieceInterv pInterv = new RowTablePieceInterv(getNomPieceByID(lp.getPieceId()), "" + lp.getNombre());
            listPieceInterv.add(pInterv);
        }

        _pieceUseInterv.setWrappedData(listPieceInterv);

        return _pieceUseInterv;
    }
    
    public String voirIntervAReal() {
        return ConstantsPages.TECHNICIEN_A_REAL_PAGE;
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

    public Intervention getNewIntervention() {
        return _newIntervention;
    }

    public DataModel getPieceUseInterv() {
        return _pieceUseInterv;
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

    public void setNewIntervention(Intervention _newIntervention) {
        this._newIntervention = _newIntervention;
    }

    public void setPieceUseInterv(DataModel _pieceUseInterv) {
        this._pieceUseInterv = _pieceUseInterv;
    }
}
