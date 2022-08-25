package Duke;

import java.util.Objects;

/**
 * Parser class helps to interpret user inputs.
 */
public class Parser {

    /**
     * translates user inputs into Duke.Command objects.
     *
     * @param input user input
     * @return Duke.Command equivalent
     */
    public Duke.Commands parseCommand(String input) {
        input = input.trim();
        if (Objects.equals(input.toLowerCase(), "bye")) {
            return Duke.Commands.BYE;
        } else if (Objects.equals(input.toLowerCase(), "list")) {
            return Duke.Commands.LIST;
        } else if (input.startsWith("mark")) {
            return Duke.Commands.MARK;
        } else if (input.startsWith("unmark")) {
            return Duke.Commands.UNMARK;
        } else if (input.startsWith("delete")) {
            return Duke.Commands.DELETE;
        } else {
            return Duke.Commands.CREATETASK;
        }
    }

}