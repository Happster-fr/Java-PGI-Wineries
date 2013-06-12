/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utilities;

import fr.epsi.enums.TypeContrat;
import java.util.Calendar;
import java.util.Date;

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

    static public Date changeDate(Date toChange, String type) {
        Calendar today_plus_year = Calendar.getInstance();
        today_plus_year.setTime(toChange);
        if (type.equals(TypeContrat.ANNUEL.getTypeContratInsert())) {
            today_plus_year.add(Calendar.YEAR, 1);
        } else if (type.equals(TypeContrat.HEBDOMADAIRE.getTypeContratInsert())) {
            today_plus_year.add(Calendar.WEEK_OF_YEAR, 1);
        } else {
            today_plus_year.add(Calendar.MONTH, 1);
        }
        return today_plus_year.getTime();
    }
}
