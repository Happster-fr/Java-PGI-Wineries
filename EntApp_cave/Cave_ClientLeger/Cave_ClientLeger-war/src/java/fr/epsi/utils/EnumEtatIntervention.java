/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

/**
 *
 * @author Quentin ecale
 */
public enum EnumEtatIntervention {

    VALIDEE("Validee"),
    PLANIFIE("Planifiee"),
    DEBUTEE("Debutee"),
    TERMINEE("Terminee");

    private final String valToInsert;

    EnumEtatIntervention(String val) {
        this.valToInsert = val;
    }

    public String getEnumEtatIntervention() {
        return this.valToInsert;
    }
}

