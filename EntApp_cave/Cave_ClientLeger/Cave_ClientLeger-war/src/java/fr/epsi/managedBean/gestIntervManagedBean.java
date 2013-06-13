/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import fr.epsi.sessionBean.gestionPieceSessionBeanRemote;
import fr.epsi.utils.ConstantsPages;
import fr.epsi.utils.EnumEtatIntervention;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
    private DataModel _listIntervention;
    private DataModel _listInterventionTech;
    private Map<String, Integer> _mapPiece;//les remplir a chaque ajout/modif qui en ont besoin pour les avoir à jour
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
     * Get the list of all intervention in database
     *
     * @return DataModel containing all interventions
     */
    public DataModel getListAllIntervention() {
        if (_listIntervention == null) {
            _listIntervention = new ListDataModel();
            _listIntervention.setWrappedData(_gestIntervBean.getAllIntervention());
        }

        return _listIntervention;
    }

    /**
     * get intervention of current technicien by the session
     * @return DataModel containing the list of intervention from the actual connected technicien
     */
    public DataModel getInterventionCurrentTech(){
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean bean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);        
        
        int idTech = bean.getTechnicien().getTechnicienId();//1;//ManagedBeanDeLogin.personne.getIdTEch
        if (_listInterventionTech == null) {
            _listInterventionTech = new ListDataModel();
            _listInterventionTech.setWrappedData(_gestIntervBean.getListInterventionTechnicien(idTech));
        }
        
        return _listInterventionTech;
    }

    /**
     * called after clic on voir button in the list intervention table to get
     * all intervention's details
     *
     * @return String corresponding of the name of the page we go
     */
    public String voirIntervention() {
        _detailInterv = (Intervention) _listInterventionTech.getRowData();
        _listPieceInterv = _gestIntervBean.getListPieceIntervention(_detailInterv);
        return ConstantsPages.TECHNICIEN_AVANCEMENT_PAGE;
    }
    
    /**
     * record changes done on the intervention
     * @return String corresponding to the list of all intervention of the technicien
     */
    public String enregistrerAvancement() {
        _gestIntervBean.modifyIntervention(_detailInterv);
        return ConstantsPages.TECHNICIEN_A_REAL_PAGE;
    }
    
    public String ajoutePieceIntervention() {
        //verifier stock
        
        //decrementer stock
        //approvisionner intervention
        
        return ConstantsPages.TECHNICIEN_AVANCEMENT_PAGE;
    }
    
    public List<EnumEtatIntervention> getAllEtatInterv() {
        return Arrays.asList(EnumEtatIntervention.values());
    }
    
    
    public String getNomPieceByID(int idPiece)
    {        
        return _gestPieceBean.getPieceById(idPiece).getNom();
    }

    public InitialContext getIc() {
        return _ic;
    }

    public DataModel getListIntervention() {
        return _listIntervention;
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

    public Intervention getNewIntervention() {
        return _newIntervention;
    }

    public void setIc(InitialContext _ic) {
        this._ic = _ic;
    }

    public void setListIntervention(DataModel _listIntervention) {
        this._listIntervention = _listIntervention;
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
}
