package duke;

enum CommandType {
    LOAD("load"),
    LIST("list"),
    MARK("mark"),
    UNMARK("mark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    BYE("bye");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return this.command;
    }
}
