package component;

/**
 * Command enumeration that lists the type of command the user inputs in the command line.
 */
public enum Command {
    CREATE_EVENT,
    CREATE_DEADLINE,
    CREATE_TODO,
    DELETE,
    MARK,
    UNMARK,
    LIST,
    FIND,
    EDIT,
    HELP,
    EXIT,
    UNKNOWN
}
