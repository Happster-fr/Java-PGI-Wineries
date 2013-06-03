/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
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
    private Map<String, Integer> _mapClient;
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean;
    private Intervention _detailInterv;
    private List<ListePiece> _listPieceInterv;
    private Intervention _newIntervention;    
    private Map<String, Integer> _mapPiece;

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

    public DataModel getListAllIntervention() {
        if (_listIntervention == null) {
            _listIntervention = new ListDataModel();
            _listIntervention.setWrappedData(_gestIntervBean.getAllIntervention());
        }

        return _listIntervention;
    }
    
    public String addIntervention() {
        _newIntervention = new Intervention();
        return "addIntervention";
    }
    
    public String createIntervention() {
        _gestIntervBean.createIntervention(_newIntervention);
        _listIntervention.setWrappedData(_gestIntervBean.getAllIntervention());
        return "listIntervention";
    }
    
    public String detailsIntervention() {
        _detailInterv = (Intervention) _listIntervention.getRowData();
        _listPieceInterv = _gestIntervBean.getListPieceIntervention(_detailInterv);
        return "detail";
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
