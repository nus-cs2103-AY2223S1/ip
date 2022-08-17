package enums;

public enum Command {
    TODO ("todo"),
    EVENT ("event"),
    DEADLINE ("deadline"),
    MARK ("mark"),
    UNMARK ("unmark"),
    LIST ("list"),
    BYE ("bye");

    private final String name;

    private Command(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
