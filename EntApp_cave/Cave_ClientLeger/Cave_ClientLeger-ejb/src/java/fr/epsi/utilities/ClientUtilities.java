/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utilities;

import fr.epsi.enums.TypeContrat;

/**
 *
 * @author Antho
 */
public class ClientUtilities {
    
    public static TypeContrat getTypeContractByString(String typeContrat) {
        if (typeContrat.equalsIgnoreCase(TypeContrat.ANNUEL.getTypeContratInsert())) {
            return TypeContrat.ANNUEL;
        } else if (typeContrat.equalsIgnoreCase(TypeContrat.MENSUEL.getTypeContratInsert())) {
            return TypeContrat.MENSUEL;
        } else if (typeContrat.equalsIgnoreCase(TypeContrat.HEBDOMADAIRE.getTypeContratInsert())) {
            return TypeContrat.HEBDOMADAIRE;
        }
        return null;
    }
}
