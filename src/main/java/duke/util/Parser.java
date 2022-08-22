package duke.util;

import duke.command.Command;
import duke.command.EmptyCommand;

public class Parser {

    public static Command parse(String input) {
        if (input.length() == 0) {
            return new EmptyCommand();
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String[] args;
        return null;
    }
}
