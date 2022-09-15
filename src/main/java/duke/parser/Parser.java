package duke.parser;

import java.util.Scanner;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;

/**
 * Parser to handle user input.
 */
public class Parser {

    /** Scanner to take in user input. */
    private Scanner in;

    /**
     * Constructor for parser object.
     * @param in Scanner object that reads user input.
     */
    public Parser(Scanner in) {
        this.in = in;
    }

    /**
     * Handles user input and calls methods accordingly, was originally for CLI
     * version, but kept for test cases.
     *
     * @throws DukeException In the event that the command is not recognised.
     */
    public void handleInput() throws DukeException {
        while (in.hasNext()) {
            String[] temp = in.nextLine().trim().split(" ", 2);
            String[] next = new String[2];
            for (int i = 0; i < temp.length; i++) {
                next[i] = temp[i].trim().toLowerCase();
            }
            identifyCommand(next).executeCommand();
        }
    }

    /**
     * Handles user input and calls methods accordingly (GUI).
     *
     * @return String that represents the output from Duke.
     * @throws DukeException In the event that the command is not recognised.
     */
    public Command handleGuiInput(String input) throws DukeException {

        String[] temp = input.trim().split(" ", 2);
        String[] next = new String[2];
        for (int i = 0; i < temp.length; i++) {
            next[i] = temp[i].trim().toLowerCase();
        }

        Command action = identifyCommand(next);
        return action;
    }


    /**
     * Identifies command and calls appropriate functions.
     *
     * @param input User input packaged into a string array.
     */
    public Command identifyCommand(String[] input) throws DukeException {
        String command = input[0];

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "unmark":
            return new UnmarkCommand(input[1]);
        case "mark":
            return new MarkCommand(input[1]);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(input);
        case "delete":
            return new DeleteCommand(input[1]);
        case "find":
            return new FindCommand(input[1]);
        case "help":
            return new HelpCommand();
        default:
            throw new DukeException("Invalid command");
        }
    }
}

