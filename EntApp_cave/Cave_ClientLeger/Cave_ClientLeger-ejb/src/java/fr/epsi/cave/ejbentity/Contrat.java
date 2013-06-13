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
 * @author Antho
 */
@Entity
@Table(name = "contrat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrat.findAll", query = "SELECT c FROM Contrat c"),
    @NamedQuery(name = "Contrat.findByContratId", query = "SELECT c FROM Contrat c WHERE c.contratId = :contratId"),
    @NamedQuery(name = "Contrat.findByDateDebut", query = "SELECT c FROM Contrat c WHERE c.dateDebut = :dateDebut"),
    @NamedQuery(name = "Contrat.findByDateFin", query = "SELECT c FROM Contrat c WHERE c.dateFin = :dateFin"),
    @NamedQuery(name = "Contrat.findByType", query = "SELECT c FROM Contrat c WHERE c.type = :type"),
    //don't work when order by DESC......
    @NamedQuery(name = "Contrat.findLastByFkClientId", query = "SELECT c FROM Contrat c WHERE c.fkClientId = :fkClientId AND c.dateFin > :datenow ORDER BY c.dateDebut"),
    @NamedQuery(name = "Contrat.findByFkClientId", query = "SELECT c FROM Contrat c WHERE c.fkClientId = :fkClientId")})
public class Contrat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CONTRAT_ID")
    private Integer contratId;
    @Column(name = "DATE_DEBUT")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "DATE_FIN")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @Size(max = 12)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_CLIENT_ID")
    private int fkClientId;

    public Contrat() {
    }
    
    public Contrat(Contrat newContrat){
        this.dateDebut = newContrat.getDateDebut();
        this.dateFin = newContrat.getDateFin();
        this.fkClientId = newContrat.getFkClientId();
        this.type = newContrat.getType(); 
    }

    public Contrat(Integer contratId) {
        this.contratId = contratId;
    }

    public Contrat(Integer contratId, int fkClientId) {
        this.contratId = contratId;
        this.fkClientId = fkClientId;
    }

    public Integer getContratId() {
        return contratId;
    }

    public void setContratId(Integer contratId) {
        this.contratId = contratId;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFkClientId() {
        return fkClientId;
    }

    public void setFkClientId(int fkClientId) {
        this.fkClientId = fkClientId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratId != null ? contratId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrat)) {
            return false;
        }
        Contrat other = (Contrat) object;
        if ((this.contratId == null && other.contratId != null) || (this.contratId != null && !this.contratId.equals(other.contratId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.epsi.cave.ejbentity.Contrat[ contratId=" + contratId + " ]";
    }
    
}
