package duke;

import Command.Command;
import Command.ExitCommand;

public class Parser {
    public static Command parse(String input) {
        String inputCommand = input.split()
        if (input.equals("exit")) {
            return new ExitCommand();
        }
    }
}
