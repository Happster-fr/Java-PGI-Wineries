/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.cave.ejbentity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Quentin ecale
 */
@Entity
@Table(name = "intervention")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Intervention.findAll", query = "SELECT i FROM Intervention i"),
    @NamedQuery(name = "Intervention.findByInterventionId", query = "SELECT i FROM Intervention i WHERE i.interventionId = :interventionId"),
    @NamedQuery(name = "Intervention.findByEtat", query = "SELECT i FROM Intervention i WHERE i.etat = :etat"),
    @NamedQuery(name = "Intervention.findByNature", query = "SELECT i FROM Intervention i WHERE i.nature = :nature"),
    @NamedQuery(name = "Intervention.findByDate", query = "SELECT i FROM Intervention i WHERE i.date = :date"),
    @NamedQuery(name = "Intervention.findByType", query = "SELECT i FROM Intervention i WHERE i.type = :type"),
    @NamedQuery(name = "Intervention.findByFkClientId", query = "SELECT i FROM Intervention i WHERE i.fkClientId = :fkClientId"),
    @NamedQuery(name = "Intervention.findByFkTechnicienId", query = "SELECT i FROM Intervention i WHERE i.fkTechnicienId = :fkTechnicienId"),
    @NamedQuery(name = "Intervention.findByNoDateForTechnicien", query = "SELECT i FROM Intervention i WHERE i.date = :date AND i.fkTechnicienId <> :fkTechnicienId")})
public class Intervention implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "INTERVENTION_ID")
    private Integer interventionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ETAT")
    private String etat;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NATURE")
    private String nature;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "FK_CLIENT_ID")
    private Integer fkClientId;
    @Column(name = "FK_TECHNICIEN_ID")
    private Integer fkTechnicienId;

    public Intervention() {
    }

    public Intervention(Integer interventionId) {
        this.interventionId = interventionId;
    }

    public Intervention(Integer interventionId, String etat, String nature, String type) {
        this.interventionId = interventionId;
        this.etat = etat;
        this.nature = nature;
        this.type = type;
    }
    
    public Intervention(String etat, String nature, String type, Date dateInterv) {
        this.etat = etat;
        this.nature = nature;
        this.type = type;
        this.date = dateInterv;
    }
    
     public Intervention(Intervention intervention){
        this.etat = intervention.getEtat();
        this.nature = intervention.getNature();
        this.type = intervention.getType();
        this.date = intervention.getDate();
    }

    public Integer getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(Integer interventionId) {
        this.interventionId = interventionId;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFkClientId() {
        return fkClientId;
    }

    public void setFkClientId(Integer fkClientId) {
        this.fkClientId = fkClientId;
    }

    public Integer getFkTechnicienId() {
        return fkTechnicienId;
    }

    public void setFkTechnicienId(Integer fkTechnicienId) {
        this.fkTechnicienId = fkTechnicienId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interventionId != null ? interventionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Intervention)) {
            return false;
        }
        Intervention other = (Intervention) object;
        if ((this.interventionId == null && other.interventionId != null) || (this.interventionId != null && !this.interventionId.equals(other.interventionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.epsi.cave.ejbentity.Intervention[ interventionId=" + interventionId + " ]";
    }
    
}
