package duke.parser;

import java.time.format.DateTimeParseException;

import duke.commands.*;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;



/**
 * Handler class that manages user input to duke.Duke.
 * TODO more JavaDocs
 */
public class DukeParser {

    private final String BREAK_LINES = "=================================";

    private TaskList taskList;
    private String keyword;
    private String restOfInputString;

    /**
     * Default constructor for the DukeParser object.
     *
     * @param taskList A reference of duke.Duke's ArrayList of Tasks
     */
    public DukeParser(TaskList taskList) {
        this.taskList = taskList;
    }


    /**
     * Handles user input, and preps parser for instruction execution.
     *
     * @param inputString The string that we would like to parse.
     */
    public void parseInput(String inputString)  {

        // Sanitise user input first before continuing
        String newString = sanitiseUserInput(inputString);

        // Grab keyword from instruction
        this.keyword = newString.split(" ")[0].toLowerCase();

        // Split rest of input string for further parsing
        // If there is no rest of input string, then restOfInputString will be "".
        this.restOfInputString = newString.substring(this.keyword.length()).trim();

    }

    /**
     * Executes a loaded and parsed instruction.
     *
     * @param st Storage that we want to save data to after the instruction has been executed
     * @throws DukeException if instruction execution fails
     */
    public Command execute() throws DukeException {
        if (this.keyword == null) {
            throw new DukeException("Error: Parser has not been loaded with an instruction yet!");
        }

        if (this.keyword.equals("")) {
            System.out.println(BREAK_LINES);
            throw new DukeException("I can't do anything based off a blank instruction!");
        }

        switch (this.keyword) {
        case "list":
            return this.listInstructionHandler();
            // Intentional Fallthrough
        case "bye":
            return this.byeInstructionHandler();
            // Intentional Fallthrough
        case "find":
            return this.findInstructionHandler();
            // Intentional Fallthrough
        case "mark":
            // Intentional Fallthrough
        case "unmark":
            // Intentional Fallthrough
        case "delete":
            return this.numericalInstructionHandler();
            // Intentional Fallthrough
        case "todo":
            // Intentional fallthrough
        case "event":
            // Intentional fallthrough
        case "deadline":
            return this.addTaskInstructionHandler();
            // Intentional Fallthrough
        default:
            throw new DukeException("Command not recognised. Try again?");
        }
    }

    /**
     * Sanitises user input.
     *
     * @param inputString User input that needs to be sanitised
     * @return Sanitised user input
     */
    public String sanitiseUserInput(String inputString) {
        // Clear trailing whitespace
        String out = inputString.trim();
        return out;
    }

    /**
     * Handles a list instruction by printing user's tasks to the screen.
     */
    public Command listInstructionHandler() {
        return new ListCommand();
    }

    /**
     * Handles a bye instruction by exiting Duke.
     */
    public Command byeInstructionHandler() {
        return new ByeCommand();
    }

    /**
     * Handles numerical instructions that act on an indexed element in taskList
     *
     * @throws DukeException Index is invalid
     */
    public Command numericalInstructionHandler() throws DukeException {

        // First, try to parse the numerical part of the instruction, if error, throw it
        int instructionNum;
        try {
            instructionNum = Integer.valueOf(this.restOfInputString) - 1;
        } catch (Exception e) {
            throw new DukeException("Error when parsing user input - did you supply a valid "
                    + "number as an index?");
        }

        // Actual logic
        System.out.println(BREAK_LINES);
        if (instructionNum >= this.taskList.getSize() || instructionNum < 0) {
            throw new DukeException("Invalid index provided. Try again?");
        }

        switch (this.keyword) {
        case "mark":
            return new MarkCommand(instructionNum);
            // Intentional Fallthrough
        case "unmark":
            return new UnmarkCommand(instructionNum);
            // Intentional Fallthrough
        default:
            return new DeleteCommand(instructionNum);
            // Intentional Fallthrough
        }

    }

    /**
     * Handles find instructions on the taskList
     *
     * @throws DukeException If no valid search parameters are found in user input
     */
    public Command findInstructionHandler() throws DukeException {
        if (this.restOfInputString.equals("")) {
            throw new DukeException("Oops! Please provide a valid string to search for.");
        }
        return new FindCommand(this.restOfInputString);
    }

    /**
     * Handles adding of instructions to taskList
     *
     * @throws DukeException User input provided is incomplete / does not match required format.
     */
    public Command addTaskInstructionHandler() throws DukeException {
        if (this.restOfInputString.equals("")) {
            throw new DukeException("Oops! Descriptions for tasks cannot be blank!");
        }

        int slashIndex = this.restOfInputString.indexOf("/");
        String divider = null;
        String[] splitInput = this.restOfInputString.split(" ");

        for (String s: splitInput) {
            if (s.startsWith("/")) {
                divider = s;
            }
        }

        Task newTask = null;
        switch (this.keyword) {
        case "todo":
            newTask = new Todo(this.restOfInputString);
            break;
        case "event":
            if (divider == null || !divider.equals("/at")) {
                throw new DukeException("Oops! To create an event, please format your input in "
                        + "this manner:\n<Event Name> /at dd-mm-yyyy hh:mm");
            }
            try {
                newTask = new Event(this.restOfInputString.substring(0, slashIndex - 1),
                        this.restOfInputString.substring(slashIndex + 4));
            } catch (DateTimeParseException e) {
                throw new DukeException("Oops! Events must have a date of occurrence, formatted "
                        + "as dd-mm-yyyy hh:mm.");
            }
            break;
        case "deadline":
            if (divider == null || !divider.equals("/by")) {
                throw new DukeException("Oops! To create a deadline, please format your input in "
                        + "this manner:\n<Deadline Name> /by dd-mm-yyyy hh:mm");
            }
            try {
                newTask = new Deadline(this.restOfInputString.substring(0, slashIndex - 1),
                        this.restOfInputString.substring(slashIndex + 4));
            } catch (DateTimeParseException e) {
                throw new DukeException("Oops! Deadlines must have a valid deadline, "
                        + "formatted as dd-mm-yyyy hh:mm.");
            }

            break;
        }

        return new AddCommand(newTask);

    }

}
