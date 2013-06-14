package fr.epsi.enums;

/**
 * Enum provides states of Interventions
 *
 * @author Quentin ecale
 */
public enum EtatIntervention {

    VALIDEE("Validée"),
    PLANIFIE("Planifiée"),
    DEBUTEE("Débutée"),
    TERMINEE("Terminée");
    private final String valToInsert;

    EtatIntervention(String val) {
        this.valToInsert = val;
    }

    public String getEnumEtatIntervention() {
        return this.valToInsert;
    }
}
