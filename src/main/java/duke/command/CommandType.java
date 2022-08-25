package duke.command;

import duke.Duke;

public enum CommandType {
    EXIT(Duke.EXIT_COMMAND_STRING),
    DISPLAY_LIST(Duke.DISPLAY_LIST_COMMAND_STRING),
    MARK_DONE(Duke.MARK_DONE_COMMAND_STRING),
    MARK_UNDONE(Duke.MARK_UNDONE_COMMAND_STRING),
    DELETE(Duke.DELETE_COMMAND_STRING),
    FIND(Duke.FIND_COMMAND_STRING),
    ADD_EVENT(Duke.ADD_EVENT_COMMAND_STRING),
    ADD_TODO(Duke.ADD_TODO_COMMAND_STRING),
    ADD_DEADLINE(Duke.ADD_DEADLINE_COMMAND_STRING);

    private String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    String getCommandString() {
        return commandString;
    }
}
