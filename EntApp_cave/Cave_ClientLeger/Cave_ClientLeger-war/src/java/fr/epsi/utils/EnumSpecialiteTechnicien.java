/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

import java.io.Serializable;

/**
 *
 * @author Mikhael
 */
public enum EnumSpecialiteTechnicien implements Serializable{
    INFORMATIQUE("Informatique"),
    MECANIQUE("Magnetique"),
    ELECTROMECANIQUE("Electromecanique");

    private final String valToInsert;

    EnumSpecialiteTechnicien(String val) {
        this.valToInsert = val;
    }

    public String getTypeIntervention() {
        return this.valToInsert;
    }
    
}
