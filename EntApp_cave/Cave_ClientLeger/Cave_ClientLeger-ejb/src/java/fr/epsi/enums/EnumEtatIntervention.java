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

    VALIDEE("Validée"),
    PLANIFIE("Planifiée"),
    DEBUTEE("Débutée"),
    TERMINEE("Terminée");

    private final String valToInsert;

    EnumEtatIntervention(String val) {
        this.valToInsert = val;
    }

    public String getEnumEtatIntervention() {
        return this.valToInsert;
    }
}

