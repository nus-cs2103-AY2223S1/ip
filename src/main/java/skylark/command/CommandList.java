package skylark.command;

/** List of commands available for the user. */
public enum CommandList {
    COMMAND_BYE("bye"), COMMAND_LIST("list"), COMMAND_DONE("mark"),
    COMMAND_UNDONE("unmark"), COMMAND_DEADLINE("deadline"),
    COMMAND_TODO("todo"), COMMAND_EVENT("event"),
    COMMAND_DELETE("delete"), COMMAND_FIND("find"), COMMAND_TAG("tag");

    private final String name;

    CommandList(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
