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

    //List of customised error messages
    private static String emptyInputError = "________________________________________\n"
            + "Empty strings or blanks are invalid!\n"
            + "________________________________________";

    private static String deleteNullError = "________________________________________\n"
            + "OOPS!!! The task number for deleting must be specified!\n"
            + "________________________________________";

    private static String deleteInvalidError = "________________________________________\n"
            + "OOPS!!! Please input a number after delete keyword!\n"
            + "________________________________________";

    private static String markNullError = "________________________________________\n"
            + "OOPS!!! The task number for marking must be specified!\n"
            + "________________________________________";

    private static String markInvalidError = "________________________________________\n"
            + "OOPS!!! Please input a number after mark keyword!\n"
            + "________________________________________";

    private static String unmarkNullError = "________________________________________\n"
            + "OOPS!!! The task number for unmarking must be specified!\n"
            + "________________________________________";

    private static String unmarkInvalidError = "________________________________________\n"
            + "OOPS!!! Please input a number after unmark keyword!\n"
            + "________________________________________";

    private static String todoNullError = "________________________________________\n"
            + "OOPS!!! The description of a todo cannot be empty.\n"
            + "________________________________________";

    private static String deadlineNullError = "________________________________________\n"
            + "OOPS!!! The description of a deadline cannot be empty.\n"
            + "________________________________________";

    private static String eventNullError = "________________________________________\n"
            + "OOPS!!! The description of an event cannot be empty.\n"
            + "________________________________________";

    private static String findNullError = "________________________________________\n"
            + "OOPS!!! The keyword to search cannot be empty.\n"
            + "________________________________________";

    /**
     * Creates an instance of a Parser.
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
            throw new DukeException(emptyInputError);
        }

        //To obtain the first word in the user-input, used to check for keyword/command
        String[] array = fullCommand.split(" ", 2);
        String firstText = array[0];

        if (fullCommand.equals(EXITCOMMAND)) {
            return handleExitCommand();
        } else if (fullCommand.equals(LISTCOMMAND)) {
            return handleListCommand();
        } else if (fullCommand.equals(UNDO)) {
            return handleUndoCommand();
        } else if (fullCommand.equals(HELPCOMMAND)) {
            return handleHelpCommand();
        } else if (firstText.equals(DELETECOMMAND)) {
            return handleDeleteCommand(array);
        } else if (firstText.equals(MARKCOMMAND)) {
            return handleMarkCommand(array);
        } else if (firstText.equals(UNMARKCOMMAND)) {
            return handleUnmarkCommand(array);
        } else if (firstText.equals(TODO)) {
            return handleTodoCommand(array);
        } else if (firstText.equals(DEADLINE)) {
            return handleDeadlineCommand(array);
        } else if (firstText.equals(EVENT)) {
            return handleEventCommand(array);
        } else if (firstText.equals(FINDCOMMAND)) {
            return handleFindCommand(array);
        } else {
            return new WrongCommand();
        }
    }

    /**
     * Handles the parsing of an ExitCommand.
     *
     * @return ExitCommand when user input "bye"
     */
    public static ExitCommand handleExitCommand() {
        setAttributes("wrong", null, -1);
        return new ExitCommand();
    }

    /**
     * Handles the parsing of a ListCommand.
     *
     * @return ListCommand when user input "list"
     */
    public static ListCommand handleListCommand() {
        setAttributes("wrong", null, -1);
        return new ListCommand();
    }

    /**
     * Handles the parsing of a UndoCommand.
     *
     * @return Command if user input "undo"
     */
    public static Command handleUndoCommand() {
        Command c = new UndoCommand(commandToUndo, taskDescription, taskNumber);
        setAttributes("wrong", null, -1);
        return c;
    }

    /**
     * Handles the parsing of a HelpCommand.
     *
     * @return HelpCommand if user input "help"
     */
    public static HelpCommand handleHelpCommand() {
        setAttributes("wrong", null, -1);
        return new HelpCommand();
    }

    /**
     * Handles the parsing of a DeleteCommand.
     *
     * @param array String array containing the command details
     * @return DeleteCommand if the user inputs a valid command
     * @throws DukeException if the user inputs an invalid command
     */
    public static DeleteCommand handleDeleteCommand(String[] array) throws DukeException {
        if (array.length == 1) {
            throw new DukeException(deleteNullError);
        }

        try {
            int inputNumber = Integer.parseInt(array[1]);
            setAttributes(DELETECOMMAND, null, inputNumber);
            return new DeleteCommand(inputNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(deleteInvalidError);
        }
    }

    /**
     * Handles the parsing of a MarkCommand.
     *
     * @param array String array containing the command details
     * @return MarkCommand if the user inputs a valid command
     * @throws DukeException if the user inputs an invalid command
     */
    public static MarkCommand handleMarkCommand(String[] array) throws DukeException {
        if (array.length == 1) {
            throw new DukeException(markNullError);
        }

        try {
            int inputNumber = Integer.parseInt(array[1]);
            setAttributes(MARKCOMMAND, null, inputNumber);
            return new MarkCommand(inputNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(markInvalidError);
        }
    }

    /**
     * Handles the parsing of a UnmarkCommand.
     *
     * @param array String array containing the command details
     * @return UnmarkCommand if the user inputs a valid command
     * @throws DukeException if the user inputs an invalid command
     */
    public static UnmarkCommand handleUnmarkCommand(String[] array) throws DukeException {
        if (array.length == 1) {
            throw new DukeException(unmarkNullError);
        }

        try {
            int inputNumber = Integer.parseInt(array[1]);
            setAttributes(UNMARKCOMMAND, null, inputNumber);
            return new UnmarkCommand(inputNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(unmarkInvalidError);
        }
    }

    /**
     * Handles the parsing of a TodoCommand.
     *
     * @param array String array containing the command details
     * @return AddCommand if the user inputs a valid command
     * @throws DukeException if the user inputs an invalid command
     */
    public static AddCommand handleTodoCommand(String[] array) throws DukeException {
        if (array.length == 1) {
            throw new DukeException(todoNullError);
        }
        setAttributes(TODO, null, -1);
        return new AddCommand(0, array[1]);
    }

    /**
     * Handles the parsing of a DeadlineCommand.
     *
     * @param array String array containing the command details
     * @return AddCommand if the user inputs a valid command
     * @throws DukeException if the user inputs an invalid command
     */
    public static AddCommand handleDeadlineCommand(String[] array) throws DukeException {
        if (array.length == 1) {
            throw new DukeException(deadlineNullError);
        }
        setAttributes(DEADLINE, null, -1);
        return new AddCommand(1, array[1]);
    }

    /**
     * Handles the parsing of an EventCommand.
     *
     * @param array String array containing the command details
     * @return AddCommand if the user inputs a valid command
     * @throws DukeException if the user inputs an invalid command
     */
    public static AddCommand handleEventCommand(String[] array) throws DukeException {
        if (array.length == 1) {
            throw new DukeException(eventNullError);
        }
        setAttributes(EVENT, null, -1);
        return new AddCommand(2, array[1]);
    }

    /**
     * Handles the parsing of a FindCommand.
     *
     * @param array String array containing the command details
     * @return FindCommand if the user inputs a valid command
     * @throws DukeException if the user inputs an invalid command
     */
    public static FindCommand handleFindCommand(String[] array) throws DukeException {
        setAttributes("wrong", null, -1);
        if (array.length == 1) {
            throw new DukeException(findNullError);
        }

        // User can input multiple keywords separated by a comma "," e.g. find book, clean, concert
        String[] searchKeywords = array[1].split(",");
        return new FindCommand(searchKeywords);
    }

    /**
     * Sets the details of the previous commands into the 3 static fields.
     *
     * @param commandToUndo String representation of the type of command to undo
     * @param taskDescription String representation of task details
     * @param taskNumber Task number to execute command on
     */
    public static void setAttributes(String commandToUndo, String taskDescription, int taskNumber) {
        Parser.commandToUndo = commandToUndo;
        Parser.taskDescription = taskDescription;
        Parser.taskNumber = taskNumber;
    }

    /**
     * Sets the taskDescription.
     *
     * @param description String representation of the task.
     */
    public static void setTaskDescription(String description) {
        Parser.taskDescription = description;
    }
}
