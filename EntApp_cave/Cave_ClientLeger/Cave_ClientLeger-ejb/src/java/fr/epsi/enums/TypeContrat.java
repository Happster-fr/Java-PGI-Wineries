/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.enums;

/**
 *
 * @author Antho
 */
public enum TypeContrat {

    ANNUEL("annuel"),
    MENSUEL("mensuel"),
    HEBDOMADAIRE("hebdomadaire");
    private final String valToInsert;

    TypeContrat(String val) {
        this.valToInsert = val;
    }

    public String getTypeContratInsert() {
        return this.valToInsert;
    }  
}
