package duke.command;

public enum CommandType {
    UNKNOWN(""),
    ERROR(null),
    EXIT("bye"),
    DISPLAY_LIST("list"),
    MARK_DONE("mark"),
    MARK_UNDONE("unmark"),
    DELETE("delete"),
    FIND("find"),
    ADD_EVENT("event"),
    ADD_TODO("todo"),
    ADD_DEADLINE("deadline");

    private String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    @Override
    public String toString() {
        return commandString;
    }
}
