package skylark.command;

/** List of commands available for the user. */
public enum CommandList {
    COMMAND_BYE, COMMAND_LIST, COMMAND_DONE,
    COMMAND_UNDONE, COMMAND_DEADLINE,
    COMMAND_TODO, COMMAND_EVENT, COMMAND_DELETE, COMMAND_FIND;

    @Override
    public String toString() {
        switch (this) {
        case COMMAND_BYE:
            return "bye";
        case COMMAND_LIST:
            return "list";
        case COMMAND_DONE:
            return "mark";
        case COMMAND_UNDONE:
            return "unmark";
        case COMMAND_DEADLINE:
            return "deadline";
        case COMMAND_TODO:
            return "todo";
        case COMMAND_EVENT:
            return "event";
        case COMMAND_DELETE:
            return "delete";
        case COMMAND_FIND:
            return "find";
        default:
            throw new IllegalArgumentException();
        }
    }
}
