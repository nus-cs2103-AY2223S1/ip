package duke.services;

import duke.utils.Commands;

import static duke.utils.Commands.UNKNOWN;

public class Parser {

    /**
     * Parses and returns an array of Duke command components from an input.
     * @return TaskList: TaskList generated from the Tasks.
     **/
    public String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Parses and returns the type of Duke Command from an input.
     * @return TaskList: TaskList generated from the Tasks.
     **/
    public Commands parseCommand(String input) {
        String[] fullCommand = input.split(" ", 2);
        Commands command;
        try {
            command = Commands.valueOf(fullCommand[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            command = UNKNOWN;
        }
        return command;
    }
}