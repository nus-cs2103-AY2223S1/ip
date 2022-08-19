package enums;

public enum Command {

    LIST("list"),
    CHECK("check"),
    UNCHECK("uncheck"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public static Command getCommandFromValue(String value) {
        for (Command e : values()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

}
