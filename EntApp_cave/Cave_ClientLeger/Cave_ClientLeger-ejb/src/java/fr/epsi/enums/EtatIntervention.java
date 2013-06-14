package fr.epsi.enums;

/**
 * Enum provides states of Interventions
 *
 * @author Quentin ecale
 */
public enum EtatIntervention {

    VALIDEE("Validee"),
    PLANIFIE("Planifiee"),
    DEBUTEE("Debutee"),
    TERMINEE("Terminee");
    private final String valToInsert;

    EtatIntervention(String val) {
        this.valToInsert = val;
    }

    public String getEnumEtatIntervention() {
        return this.valToInsert;
    }
}
