package fr.epsi.enums;

/**
 * Enum provides states of Interventions
 *
 * @author Quentin ecale
 */
public enum EtatIntervention {

    VALIDEE("Valid�e"),
    PLANIFIE("Planifi�e"),
    DEBUTEE("D�but�e"),
    TERMINEE("Termin�e");
    private final String valToInsert;

    EtatIntervention(String val) {
        this.valToInsert = val;
    }

    public String getEnumEtatIntervention() {
        return this.valToInsert;
    }
}
