package duke.core;

import java.util.ArrayList;
import java.util.HashMap;

import duke.commands.Command;

/**
 * A class that encapsulates a parser of commands for Duke.
 * Takes in a list of commands and with a given input, invokes the
 * correct commands.
 */
public class Parser {

    /**
     * A HashMap that maps command invokers to their respective commands.
     * When receiving input, the parser looks here for commands to execute.
     */
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Constructor for a parser.
     *
     * @param commands The commands that this parser will attempt to execute.
     */
    public Parser(ArrayList<Command> commands) {
        commands.forEach(x -> this.commands.put(x.getInvoker(), x));
    }

    /**
     * Parses a command input and executes the correct command if it exists using the
     * appropriate parameters.
     *
     * @param input String input from the user.
     * @return Command output.
     * @throws DukeException If command is invalid or if any execution is erroneous.
     */
    public String parseInput(String input) throws DukeException {
        String[] command = input.split(" ", 2);
        String parameters = command.length > 1
                ? command[1]
                : "";
        Command toExecute = commands.get(command[0]);
        if (toExecute != null) {
            return toExecute.execute(parameters);
        } else {
            throw new DukeException("Invalid Command");
        }
    }
}
