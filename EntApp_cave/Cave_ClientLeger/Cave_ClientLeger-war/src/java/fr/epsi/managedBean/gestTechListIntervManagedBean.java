/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import fr.epsi.cave.ejbentity.Intervention;
import fr.epsi.cave.ejbentity.ListePiece;
import fr.epsi.sessionBean.gestionInterventionSessionBeanRemote;
import fr.epsi.sessionBean.gestionPieceSessionBeanRemote;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Quentin ecale
 */
@ManagedBean(name = "gestTechListIntervManagedBean")
@RequestScoped
public class gestTechListIntervManagedBean {
    
    private InitialContext _ic;
    private DataModel _listIntervention;
    private DataModel _listInterventionTech;
    private DataModel _listNonFinishedIntervTech;
    @EJB
    private gestionInterventionSessionBeanRemote _gestIntervBean;
    @EJB
    private gestionPieceSessionBeanRemote _gestPieceBean;
    private List<ListePiece> _listPieceInterv;

    /**
     * Creates a new instance of gestTechListIntervManagedBean
     */
    public gestTechListIntervManagedBean() {
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
     *
     * @return DataModel containing the list of intervention from the actual
     * connected technicien
     */
    public DataModel getInterventionCurrentTech() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean bean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);

        int idTech = bean.getTechnicien().getTechnicienId();
        if (_listInterventionTech == null) {
            _listInterventionTech = new ListDataModel();
            _listInterventionTech.setWrappedData(_gestIntervBean.getListInterventionTechnicien(idTech));
        }

        return _listInterventionTech;
    }

    /**
     * get all non finished intervention of the current technicien
     *
     * @return DataModel to display in view
     */
    public DataModel getNonFinishedInterventionCurrentTech() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean bean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);

        int idTech = bean.getTechnicien().getTechnicienId();
        if (_listNonFinishedIntervTech == null) {
            _listNonFinishedIntervTech = new ListDataModel();
            _listNonFinishedIntervTech.setWrappedData(_gestIntervBean.getListInterventionNonFinishedByTech(idTech));
        }

        return _listNonFinishedIntervTech;
    }

    public InitialContext getIc() {
        return _ic;
    }

    public void setIc(InitialContext _ic) {
        this._ic = _ic;
    }

    public DataModel getListIntervention() {
        return _listIntervention;
    }

    public void setListIntervention(DataModel _listIntervention) {
        this._listIntervention = _listIntervention;
    }

    public DataModel getListInterventionTech() {
        return _listInterventionTech;
    }

    public void setListInterventionTech(DataModel _listInterventionTech) {
        this._listInterventionTech = _listInterventionTech;
    }

    public DataModel getListNonFinishedIntervTech() {
        return _listNonFinishedIntervTech;
    }

    public void setListNonFinishedIntervTech(DataModel _listNonFinishedIntervTech) {
        this._listNonFinishedIntervTech = _listNonFinishedIntervTech;
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

    public List<ListePiece> getListPieceInterv() {
        return _listPieceInterv;
    }

    public void setListPieceInterv(List<ListePiece> _listPieceInterv) {
        this._listPieceInterv = _listPieceInterv;
    }
    
    
}
