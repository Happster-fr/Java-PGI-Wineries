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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Quentin ecale
 */
@Entity
@Table(name = "liste_piece")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListePiece.findAll", query = "SELECT l FROM ListePiece l"),
    @NamedQuery(name = "ListePiece.findByListePieceId", query = "SELECT l FROM ListePiece l WHERE l.listePieceId = :listePieceId"),
    @NamedQuery(name = "ListePiece.findByInterventionId", query = "SELECT l FROM ListePiece l WHERE l.interventionId = :interventionId"),
    @NamedQuery(name = "ListePiece.findByPieceId", query = "SELECT l FROM ListePiece l WHERE l.pieceId = :pieceId"),
    @NamedQuery(name = "ListePiece.findByNombre", query = "SELECT l FROM ListePiece l WHERE l.nombre = :nombre")})
public class ListePiece implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LISTE_PIECE_ID")
    private Integer listePieceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERVENTION_ID")
    private int interventionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PIECE_ID")
    private int pieceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOMBRE")
    private int nombre;

    public ListePiece() {
    }

    public ListePiece(Integer listePieceId) {
        this.listePieceId = listePieceId;
    }

    public ListePiece(Integer listePieceId, int interventionId, int pieceId, int nombre) {
        this.listePieceId = listePieceId;
        this.interventionId = interventionId;
        this.pieceId = pieceId;
        this.nombre = nombre;
    }
    
    public ListePiece(int idPiece, int idIntervention, int qteToAdd)
    {
        this.interventionId = idIntervention;
        this.pieceId = idPiece;
        this.nombre = qteToAdd;
    }

    public Integer getListePieceId() {
        return listePieceId;
    }

    public void setListePieceId(Integer listePieceId) {
        this.listePieceId = listePieceId;
    }

    public int getInterventionId() {
        return interventionId;
    }

    public void setInterventionId(int interventionId) {
        this.interventionId = interventionId;
    }

    public int getPieceId() {
        return pieceId;
    }

    public void setPieceId(int pieceId) {
        this.pieceId = pieceId;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listePieceId != null ? listePieceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListePiece)) {
            return false;
        }
        ListePiece other = (ListePiece) object;
        if ((this.listePieceId == null && other.listePieceId != null) || (this.listePieceId != null && !this.listePieceId.equals(other.listePieceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.epsi.cave.ejbentity.ListePiece[ listePieceId=" + listePieceId + " ]";
    }
    
}
