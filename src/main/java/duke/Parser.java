package duke;

import duke.Duke.Command;

public class Parser {
    protected Parser() {}

    protected String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

    protected Command parseCommand(String input) {
        String[] fullCommand = input.split(" ", 2);
        return Command.valueOf(fullCommand[0].toUpperCase());
    }
}
