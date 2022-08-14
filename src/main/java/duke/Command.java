package duke;

import utils.Constants;

public enum Command {
    TODO(-1),
    DEADLINE(-1),
    EVENT(-1),
    MARK(1),
    UNMARK(1),
    DELETE(1),
    LIST(0),
    BYE(0),
    INVALID(-1);

    private final int maxArgLength;

    Command(int maxArgLength) {
        this.maxArgLength = maxArgLength;
    }

    public static Command isValidInput(Command command, String input) throws DukeException {
        if (command.maxArgLength == -1) {
            return command;
        }

        if (input.trim().split("\\s+").length != command.maxArgLength) {
            throw new DukeException(Constants.ERROR_INVALID_ARGUMENTS);
        }

        return command;
    }

    public static Command contains(String command) {
        for (Command c : values()) {
            if (c.name().equalsIgnoreCase(command)) {
                return valueOf(command.toUpperCase());
            }
        }
        return INVALID;
    }
}
