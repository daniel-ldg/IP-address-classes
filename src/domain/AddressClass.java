package domain;

/**
 * @author Daniel Ladrón de Guevara
 */
public enum AddressClass {
    CLASS_A ("Clase A"),
    CLASS_B ("Clase B"),
    CLASS_C ("Clase C"),
    CLASS_D ("Clase D"),
    CLASS_E ("Clase E");

    private final String name;

    AddressClass(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
