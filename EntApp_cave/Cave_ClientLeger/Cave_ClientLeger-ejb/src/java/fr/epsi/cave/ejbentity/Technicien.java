/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.cave.ejbentity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Quentin ecale
 */
@Entity
@Table(name = "technicien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Technicien.findAll", query = "SELECT t FROM Technicien t"),
    @NamedQuery(name = "Technicien.findByTechnicienId", query = "SELECT t FROM Technicien t WHERE t.technicienId = :technicienId"),
    @NamedQuery(name = "Technicien.findByNom", query = "SELECT t FROM Technicien t WHERE t.nom = :nom"),
    @NamedQuery(name = "Technicien.findByPrenom", query = "SELECT t FROM Technicien t WHERE t.prenom = :prenom"),
    @NamedQuery(name = "Technicien.findByAdresse", query = "SELECT t FROM Technicien t WHERE t.adresse = :adresse"),
    @NamedQuery(name = "Technicien.findBySpecialite", query = "SELECT t FROM Technicien t WHERE t.specialite = :specialite"),
    @NamedQuery(name = "Technicien.findByLoginPassword", query = "SELECT t FROM Technicien t WHERE t.login = :login AND t.password = :password")})
public class Technicien implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWORD")
    private String password;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TECHNICIEN_ID")
    private Integer technicienId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PRENOM")
    private String prenom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ADRESSE")
    private String adresse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "SPECIALITE")
    private String specialite;

    public Technicien() {
    }

    public Technicien(Integer technicienId) {
        this.technicienId = technicienId;
    }

    public Technicien(Integer technicienId, String nom, String prenom, String adresse, String specialite) {
        this.technicienId = technicienId;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.specialite = specialite;
    }

    public Integer getTechnicienId() {
        return technicienId;
    }

    public void setTechnicienId(Integer technicienId) {
        this.technicienId = technicienId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (technicienId != null ? technicienId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Technicien)) {
            return false;
        }
        Technicien other = (Technicien) object;
        if ((this.technicienId == null && other.technicienId != null) || (this.technicienId != null && !this.technicienId.equals(other.technicienId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.epsi.cave.ejbentity.Technicien[ technicienId=" + technicienId + " ]";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
