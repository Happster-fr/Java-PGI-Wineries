package fr.epsi.enums;

/**
 * Enum provides types of contracts
 *
 * @author Anthony
 */
public enum TypeContrat {

    ANNUEL("annuel"),
    MENSUEL("mensuel"),
    HEBDOMADAIRE("hebdomadaire");
    private final String valToInsert;

    TypeContrat(String val) {
        this.valToInsert = val;
    }

    public String getTypeContratInsert() {
        return this.valToInsert;
    }
}
