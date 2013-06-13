package fr.epsi.enums;

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
