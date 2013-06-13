/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

/**
 *
 * @author Mikhael
 */
public enum EnumSpecialiteTechnicien {
    INFORMATIQUE("Informatique"),
    MECANIQUE("Magnétique"),
    ELECTROMECANIQUE("Electromécanique");

    private final String valToInsert;

    EnumSpecialiteTechnicien(String val) {
        this.valToInsert = val;
    }

    public String getTypeIntervention() {
        return this.valToInsert;
    }
    
}
