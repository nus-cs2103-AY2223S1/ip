package duke.commandword;

import duke.exception.DukeException;

/**
 * Enum for the command word
 */
public enum CommandWord {
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    NOTE("note"),
    BYE("bye");

    // String field for comparing to the input to the enum
    private final String command;

    /**
     * Constructor for the CommandWord enum.
     *
     * @param command User input command.
     */
    CommandWord(String command) {
        this.command = command;
    }

    /**
     * Returns a CommandWord based on the given input string.
     *
     * @param input Input command string
     * @return CommandWord from the enum that matches the input command
     * @throws IllegalArgumentException If an invalid command is inputted
     */
    public static CommandWord getCommand(String input) throws DukeException {
        for (int i = 0; i < CommandWord.values().length; i++) {
            CommandWord temp = CommandWord.values()[i];
            if (input.equals(temp.command)) {
                return temp;
            }
        }
        throw new DukeException("There is no such command called " + input + "!\n" + "Please try again :)\n");
    }
}
