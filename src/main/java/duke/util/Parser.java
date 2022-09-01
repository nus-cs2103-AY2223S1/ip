package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.WrongCommand;
import duke.exception.DukeException;

/**
 * Handles the process of parsing the user inputs and carry out the
 * corresponding commands.
 */
public class Parser {
    //The important keywords to check against with the user-input
    public static final String EXITCOMMAND = "bye";
    public static final String LISTCOMMAND = "list";
    public static final String MARKCOMMAND = "mark";
    public static final String UNMARKCOMMAND = "unmark";
    public static final String DELETECOMMAND = "delete";
    public static final String FINDCOMMAND = "find";

    //The Strings representing the 3 types of Tasks
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    /**
     * Default constructor to create an instance of a Parser.
     */
    public Parser() {
    }

    /**
     * Processes the user input, checks it against the specified commands and carry out
     * the valid commands depending on the keywords used.
     *
     * @param fullCommand String representing the entire line of user input
     * @return an instance of a Command corresponding to the keyword used in user input
     * @throws DukeException if an error specific to the ChatBot occurs
     */
    public static Command parse(String fullCommand) throws DukeException {
        //Exception: When the user input is empty/blank
        if (fullCommand.isEmpty() || fullCommand.isBlank()) {
            String errorMessage = "__________________________________________________\n"
                    + "Please enter valid inputs, empty strings or blanks are not valid!";
            throw new DukeException(errorMessage);
        }

        //To obtain the first word in the user-input, used to check for keyword/command
        String[] array = fullCommand.split(" ", 2);
        String firstText = array[0];

        if (fullCommand.equals(EXITCOMMAND)) {
            return new ExitCommand();
        } else if (fullCommand.equals(LISTCOMMAND)) {
            return new ListCommand();
        } else if (firstText.equals(DELETECOMMAND)) {
            if (array.length == 1) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! The task number for deleting must be specified!";
                throw new DukeException(errorMessage);
            }

            try {
                int inputNumber = Integer.parseInt(array[1]);
                return new DeleteCommand(inputNumber);
            } catch (NumberFormatException e) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! Please input a number after delete keyword!";
                throw new DukeException(errorMessage);
            }
        } else if (firstText.equals(MARKCOMMAND)) {
            if (array.length == 1) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! The task number for marking must be specified!";
                throw new DukeException(errorMessage);
            }

            try {
                int inputNumber = Integer.parseInt(array[1]);
                return new MarkCommand(inputNumber);
            } catch (NumberFormatException e) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! Please input a number after mark keyword!";
                throw new DukeException(errorMessage);
            }
        } else if (firstText.equals(UNMARKCOMMAND)) {
            if (array.length == 1) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! The task number for unmarking must be specified!";
                throw new DukeException(errorMessage);
            }

            try {
                int inputNumber = Integer.parseInt(array[1]);
                return new UnmarkCommand(inputNumber);
            } catch (NumberFormatException e) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! Please input a number after unmark keyword!";
                throw new DukeException(errorMessage);
            }
        } else if (firstText.equals(TODO)) {
            if (array.length == 1) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! The description of a todo cannot be empty.";
                throw new DukeException(errorMessage);
            }
            return new AddCommand(0, array[1]);
        } else if (firstText.equals(DEADLINE)) {
            if (array.length == 1) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! The description of a deadline cannot be empty.";
                throw new DukeException(errorMessage);
            }
            return new AddCommand(1, array[1]);
        } else if (firstText.equals(EVENT)) {
            if (array.length == 1) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! The description of a event cannot be empty.";
                throw new DukeException(errorMessage);
            }
            return new AddCommand(2, array[1]);
        } else if (firstText.equals(FINDCOMMAND)) {
            if (array.length == 1) {
                String errorMessage = "__________________________________________________\n"
                        + "OOPS!!! The keyword to search cannot be empty.";
                throw new DukeException(errorMessage);
            }
            return new FindCommand(array[1]);
        } else {
            return new WrongCommand();
        }

    }
}
