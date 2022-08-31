package duke;

import duke.Duke.Command;

/**
 * Represents the command-parsing component of MakiBot.
 *
 * @author Justin Peng
 */
public class Parser {
    /**
     * Creates a new parser.
     */
    protected Parser() {}

    /**
     * Parses the input into a command and parameters.
     *
     * @param input The input {@code String} to be parsed.
     * @return A length-2 {@code String} array with the command as the first element
     *     and the parameters as the second element.
     */
    protected String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Obtains the {@code Command} from the input.
     * If the input does not match any {@code Command}, returns {@code null}.
     *
     * @param input The input {@code String} to be parsed.
     * @return The {@code Command} in the input.
     */
    protected Command parseCommand(String input) {
        String[] fullCommand = parseFullCommand(input);
        return Command.valueOf(fullCommand[0].toUpperCase());
    }
}
