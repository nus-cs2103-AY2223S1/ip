package duke.command;

/**
 * Enum to contain all the commands in the app
 */
public enum CommandsEnum {

    BYE("bye"), LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), TODO("todo"), DEADLINE(
            "deadline"), EVENT(
                    "event"), BY("by"), FIND("FIND"), ADDCOMMAND("alias"), DELETECOMMAND("rmalias"), INVALID("");

    final String commandType;

    CommandsEnum(String type) {
        commandType = type;
    }

    @Override
    public String toString() {
        return commandType;
    }
}
