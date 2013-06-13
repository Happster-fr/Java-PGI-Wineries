/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Antho
 */
@ManagedBean(name = "visuIntervManagedBean")
@RequestScoped
public class visuIntervManagedBean {

    public visuIntervManagedBean() {
    }

    /*public DataModel getInterventionCurrentTech() {
        FacesContext context = FacesContext.getCurrentInstance();
        LoginManagedBean bean = (LoginManagedBean) context.getApplication().evaluateExpressionGet(context, "#{loginManagedBean}", LoginManagedBean.class);

        int idTech = bean.getTechnicien().getTechnicienId();//1;//ManagedBeanDeLogin.personne.getIdTEch
        if (_listInterventionTech == null) {
            _listInterventionTech = new ListDataModel();
            _listInterventionTech.setWrappedData(_gestIntervBean.getListInterventionTechnicien(idTech));
        }

        return _listInterventionTech;
    }*/
}
