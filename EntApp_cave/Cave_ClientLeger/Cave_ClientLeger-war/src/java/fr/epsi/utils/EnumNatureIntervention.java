/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

/**
 *
 * @author Quentin ecale
 */
public enum EnumNatureIntervention {
    CURATIVE("curative"),
    PREVENTIVE("préventive");

    private final String valToInsert;

    EnumNatureIntervention(String val) {
        this.valToInsert = val;
    }

    public String getNatureIntervention() {
        return this.valToInsert;
    }
}