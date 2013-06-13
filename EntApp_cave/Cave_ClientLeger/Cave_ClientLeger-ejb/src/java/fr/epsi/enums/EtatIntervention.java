/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.enums;

/**
 *
 * @author Quentin ecale
 */
public enum EtatIntervention {

    VALIDEE("Valid�e"),
    PLANIFIE("Planifi�e"),
    DEBUTEE("D�but�e"),
    TERMINEE("Termin�e");

    private final String valToInsert;

    EtatIntervention(String val) {
        this.valToInsert = val;
    }

    public String getEnumEtatIntervention() {
        return this.valToInsert;
    }
}

