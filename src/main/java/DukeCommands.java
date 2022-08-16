public enum DukeCommands {
    EXIT("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private String text;

    DukeCommands(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
