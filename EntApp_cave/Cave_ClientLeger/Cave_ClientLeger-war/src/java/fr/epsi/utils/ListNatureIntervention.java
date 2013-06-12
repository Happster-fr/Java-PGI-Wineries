/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.utils;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Quentin ecale
 */
public class ListNatureIntervention {

    public static final Map<String, String> NATURE_INTERVENTION_MAP = Collections.unmodifiableMap(
            new TreeMap<String, String>() {
                {
                    put("Curative", "curative");
                    put("Préventive", "preventive");

                }
            });
}
