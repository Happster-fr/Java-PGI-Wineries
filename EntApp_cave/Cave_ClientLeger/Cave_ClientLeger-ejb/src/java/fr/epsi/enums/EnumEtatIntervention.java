package fr.epsi.enums;

/**
 *
 * @author Quentin ecale
 */
public enum EnumEtatIntervention {

    VALIDEE("Valid�e"),
    PLANIFIE("Planifi�e"),
    DEBUTEE("D�but�e"),
    TERMINEE("Termin�e");
    private final String valToInsert;

    EnumEtatIntervention(String val) {
        this.valToInsert = val;
    }

    public String getEnumEtatIntervention() {
        return this.valToInsert;
    }
}
