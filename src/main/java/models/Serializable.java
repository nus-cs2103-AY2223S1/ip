package models;

public class Serializable {
    private static final String formatter = " | ";
    private final String serialized;

    public Serializable(String[] data) {
        this.serialized = String.join(Serializable.formatter, data).strip();
    }

    public String get() {
        return this.serialized;
    }
}
