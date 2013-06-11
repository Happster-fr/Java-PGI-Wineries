/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import fr.epsi.utils.ConstantsPages;
import java.util.List;
import java.util.Map;
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
@ManagedBean(name = "gestIntervManagedBean")
@SessionScoped
public class gestIntervManagedBean {

    private InitialContext _ic;
    private DataModel _listIntervention;
    private DataModel _listInterventionTech;
    private Map<String, Integer> _mapClient;
    private Map<String, Integer> _mapPiece;//les remplir a chaque ajout/modif qui en ont besoin pour les avoir à jour
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean; //faire une classe regroupant les beans remotes qui nous fait un singleton pour chacun ?
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

    public DataModel getInterventionCurrentTech(){
        int idTech = 1;//ManagedBeanDeLogin.personne.getIdTEch
        if (_listInterventionTech == null) {
            _listInterventionTech = new ListDataModel();
            _listInterventionTech.setWrappedData(_gestIntervBean.getListInterventionTechnicien(idTech));
        }        
        
        return _listInterventionTech;
    }

    /**
     * instanciate new intervention and redirect to creation page
     *
     * @return String corresponding to the name of the page we go
     */
    public String addIntervention() {
        _newIntervention = new Intervention();
        return ConstantsPages.INTERVENTION_ADD_PAGE;
    }

    /**
     * Creation and record in DB of intervention after click on the create
     * button and redirect to intervention list
     *
     * @return String corresponding of the name of the page we redirect after
     * action
     */
    public String createIntervention() {
        _gestIntervBean.createIntervention(_newIntervention);
        _listIntervention.setWrappedData(_gestIntervBean.getAllIntervention());
        return ConstantsPages.INTERVENTION_LIST_PAGE;
    }

    /**
     * called after clic on detail button in the list intervention table to get
     * all intervention's details
     *
     * @return String corresponding of the name of the page we go
     */
    public String detailsIntervention() {
        _detailInterv = (Intervention) _listIntervention.getRowData();
        _listPieceInterv = _gestIntervBean.getListPieceIntervention(_detailInterv);
        return ConstantsPages.INTERVENTION_DETAIL_PAGE;
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
