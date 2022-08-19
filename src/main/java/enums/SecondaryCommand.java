package enums;

public enum SecondaryCommand {

    BY("/by", 3),
    AT("/at", 3);

    private final String value;
    private final int length;

    SecondaryCommand(String value, int length) {
        this.value = value;
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public int getLength() {
        return length;
    }
}
