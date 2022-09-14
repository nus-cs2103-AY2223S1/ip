package duke.util;

enum CommandsEnum {

    BYE("bye"), LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), TODO("todo"), DEADLINE(
            "deadline"), EVENT("event"), BY("by"), FIND("FIND"), INVALID("");

    final String commandType;

    CommandsEnum(String type) {
        commandType = type;
    }

    static CommandsEnum getCommandFromString(String command) {
        for (CommandsEnum c : CommandsEnum.values()) {
            if (c.toString().equalsIgnoreCase(command)) {
                return c;
            }
        }

        return INVALID;
    }

    @Override
    public String toString() {
        return commandType;
    }
}
