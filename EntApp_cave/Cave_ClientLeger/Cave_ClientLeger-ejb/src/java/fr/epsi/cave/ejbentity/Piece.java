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
@Table(name = "piece")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piece.findAll", query = "SELECT p FROM Piece p"),
    @NamedQuery(name = "Piece.findByPieceId", query = "SELECT p FROM Piece p WHERE p.pieceId = :pieceId"),
    @NamedQuery(name = "Piece.findByNom", query = "SELECT p FROM Piece p WHERE p.nom = :nom"),
    @NamedQuery(name = "Piece.findByPrix", query = "SELECT p FROM Piece p WHERE p.prix = :prix"),
    @NamedQuery(name = "Piece.findByQteStock", query = "SELECT p FROM Piece p WHERE p.qteStock = :qteStock")})
public class Piece implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PIECE_ID")
    private Integer pieceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NOM")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIX")
    private float prix;
    @Column(name = "QTE_STOCK")
    private Integer qteStock;

    public Piece() {
    }

    public Piece(Integer pieceId) {
        this.pieceId = pieceId;
    }

    public Piece(Integer pieceId, String nom, float prix) {
        this.pieceId = pieceId;
        this.nom = nom;
        this.prix = prix;
    }

    public Integer getPieceId() {
        return pieceId;
    }

    public void setPieceId(Integer pieceId) {
        this.pieceId = pieceId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Integer getQteStock() {
        return qteStock;
    }

    public void setQteStock(Integer qteStock) {
        this.qteStock = qteStock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pieceId != null ? pieceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piece)) {
            return false;
        }
        Piece other = (Piece) object;
        if ((this.pieceId == null && other.pieceId != null) || (this.pieceId != null && !this.pieceId.equals(other.pieceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.epsi.cave.ejbentity.Piece[ pieceId=" + pieceId + " ]";
    }
    
}
