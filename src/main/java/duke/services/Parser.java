package duke.services;
import duke.utils.Commands;
import static duke.utils.Commands.UNKNOWN;

public class Parser {
    public String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

    public Commands parseCommand(String input) {
        String[] wholeCommand = input.split(" ", 2);
        Commands command;
        try {
            command = Commands.valueOf(wholeCommand[0].toUpperCase());
        } catch (IllegalArgumentException exception) {
            command = UNKNOWN;
        }
        return command;
    }
}
