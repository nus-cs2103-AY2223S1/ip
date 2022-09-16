package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UndoCommand;
import duke.command.UnmarkCommand;
import duke.command.WrongCommand;
import duke.exception.DukeException;

/**
 * Handles the process of parsing the user inputs and carry out the
 * corresponding commands.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class Parser {
    //The important keywords to check against with the user-input
    public static final String EXITCOMMAND = "bye";
    public static final String LISTCOMMAND = "list";
    public static final String MARKCOMMAND = "mark";
    public static final String UNMARKCOMMAND = "unmark";
    public static final String DELETECOMMAND = "delete";
    public static final String FINDCOMMAND = "find";
    public static final String HELPCOMMAND = "help";

    //The Strings representing the 3 types of Tasks
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    //Extensions implemented for C-Undo
    public static final String UNDO = "undo";

    //Keeps track of previous command details
    private static String commandToUndo = "wrong";
    private static String taskDescription = null;
    private static int taskNumber = -1;
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
            String errorMessage = "________________________________________\n"
                    + "Empty strings or blanks are invalid!\n"
                    + "________________________________________";
            throw new DukeException(errorMessage);
        }

        //To obtain the first word in the user-input, used to check for keyword/command
        String[] array = fullCommand.split(" ", 2);
        String firstText = array[0];

        if (fullCommand.equals(EXITCOMMAND)) {
            Parser.setToNull();
            return new ExitCommand();
        } else if (fullCommand.equals(LISTCOMMAND)) {
            Parser.setToNull();
            return new ListCommand();
        } else if (fullCommand.equals(UNDO)) {
            Command c = new UndoCommand(commandToUndo, taskDescription, taskNumber);
            Parser.setToNull();
            return c;
        } else if (fullCommand.equals(HELPCOMMAND)) {
            Parser.setToNull();
            return new HelpCommand();
        } else if (firstText.equals(DELETECOMMAND)) {
            if (array.length == 1) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! The task number for deleting must be specified!\n"
                        + "________________________________________";
                throw new DukeException(errorMessage);
            }

            try {
                int inputNumber = Integer.parseInt(array[1]);
                Parser.commandToUndo = DELETECOMMAND;
                Parser.taskDescription = null;
                Parser.taskNumber = inputNumber;
                return new DeleteCommand(inputNumber);
            } catch (NumberFormatException e) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! Please input a number after delete keyword!\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }
        } else if (firstText.equals(MARKCOMMAND)) {
            if (array.length == 1) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! The task number for marking must be specified!\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }

            try {
                int inputNumber = Integer.parseInt(array[1]);
                Parser.commandToUndo = MARKCOMMAND;
                Parser.taskDescription = null;
                Parser.taskNumber = inputNumber;
                return new MarkCommand(inputNumber);
            } catch (NumberFormatException e) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! Please input a number after mark keyword!\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }
        } else if (firstText.equals(UNMARKCOMMAND)) {
            if (array.length == 1) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! The task number for unmarking must be specified!\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }

            try {
                int inputNumber = Integer.parseInt(array[1]);
                Parser.commandToUndo = UNMARKCOMMAND;
                Parser.taskDescription = null;
                Parser.taskNumber = inputNumber;
                return new UnmarkCommand(inputNumber);
            } catch (NumberFormatException e) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! Please input a number after unmark keyword!\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }
        } else if (firstText.equals(TODO)) {
            if (array.length == 1) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! The description of a todo cannot be empty.\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }
            Parser.commandToUndo = TODO;
            Parser.taskDescription = null;
            Parser.taskNumber = -1;
            return new AddCommand(0, array[1]);
        } else if (firstText.equals(DEADLINE)) {
            if (array.length == 1) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! The description of a deadline cannot be empty.\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }
            Parser.commandToUndo = DEADLINE;
            Parser.taskDescription = null;
            Parser.taskNumber = -1;
            return new AddCommand(1, array[1]);
        } else if (firstText.equals(EVENT)) {
            if (array.length == 1) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! The description of a event cannot be empty.\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }
            Parser.commandToUndo = EVENT;
            Parser.taskDescription = null;
            Parser.taskNumber = -1;
            return new AddCommand(2, array[1]);
        } else if (firstText.equals(FINDCOMMAND)) {
            Parser.setToNull();
            if (array.length == 1) {
                String errorMessage = "________________________________________\n"
                        + "OOPS!!! The keyword to search cannot be empty.\n"
                        + "________________________________________";;
                throw new DukeException(errorMessage);
            }

            // User can input multiple keywords separated by a comma ","
            // For e.g. find book, clean, concert
            String[] searchKeywords = array[1].split(",");
            return new FindCommand(searchKeywords);
        } else {
            return new WrongCommand();
        }
    }

    public static void setToNull() {
        Parser.commandToUndo = "wrong";
        Parser.taskDescription = null;
        Parser.taskNumber = -1;
    }

    public static void setTaskDescription(String description) {
        Parser.taskDescription = description;
    }
}
