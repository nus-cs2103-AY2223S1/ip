package parser;

import java.util.List;
import java.util.ArrayList;

import tasks.Task;
import tasks.Deadline;
import tasks.Todo;
import tasks.Event;
import exceptions.DukeException;

/**
 * Handler class that manages user input to Duke.
 * TODO more JavaDocs
 */
public class DukeParser {

    private final String BREAK_LINES = "=================================";

    private List<Task> taskList;
    private String keyword;
    private String restOfInputString;
    private Boolean exitDuke = false;

    /**
     * Default constructor for the DukeParser object.
     *
     * @param taskList A reference of Duke's ArrayList of Tasks
     */
    public DukeParser(List<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean exitDuke() {
        return this.exitDuke;
    }

    /**
     * Handles user input, and preps parser for instruction execution
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

    public void execute() throws DukeException {
        if (this.keyword == null) {
            throw new DukeException("Error: Parser has not been loaded with an instruction yet!");
        }

        if (this.keyword.equals("")) {
            System.out.println(BREAK_LINES);
            throw new DukeException("I can't do anything based off a blank instruction!");
        }

        // TODO: Fix switch case indentation
        switch (this.keyword) {
        case "list":
            this.listInstructionHandler();
            break;
        case "bye":
            this.byeInstructionHandler();
            break;
        case "mark":
            this.numericalInstructionHandler();
            break;
        case "unmark":
            this.numericalInstructionHandler();
            break;
        case "delete":
            this.numericalInstructionHandler();
            break;
        case "todo":
            this.addTaskInstructionHandler();
            break;
        case "event":
            this.addTaskInstructionHandler();
            break;
        case "deadline":
            this.addTaskInstructionHandler();
            break;
        default:
            System.out.println(BREAK_LINES);
            System.out.println("Command not recognised. Try again?");
            System.out.println(BREAK_LINES);
            break;
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
    public void listInstructionHandler() {
        int counter = 1;
        System.out.println(BREAK_LINES);
        System.out.println("Here are the tasks that you have added to the list: ");
        for (Task task : this.taskList) {
            if (task != null) {
                System.out.println(counter + ". " + task);
                counter++;
            }
        }
        System.out.println(BREAK_LINES);
    }

    /**
     * Handles a bye instruction by exiting Duke.
     */
    public void byeInstructionHandler() {
        System.out.println(BREAK_LINES);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(BREAK_LINES);
        this.exitDuke = true;
    }

    /**
     * Handles numerical instructions that act on an indexed element in taskList
     * @throws DukeException Index is invalid
     */
    public void numericalInstructionHandler() throws DukeException {

        // First, try to parse the numerical part of the instruction, if error, throw it
        int instructionNum;
        try {
            instructionNum = Integer.valueOf(this.restOfInputString) - 1;
        } catch (Exception e) {
            throw new DukeException("Error when parsing user input - did you supply a valid " +
                    "number as an index?" );
        }

        // Actual logic
        System.out.println(BREAK_LINES);
        if (instructionNum >= this.taskList.size() || instructionNum < 0) {
            throw new DukeException("Invalid index provided. Try again?");
        }

        switch (this.keyword) {
        case "mark":
            this.taskList.get(instructionNum).markAsDone();
            System.out.println("Nice! I've marked this task as having been completed:");
            System.out.println(this.taskList.get(instructionNum));
            break;
        case "unmark":
            this.taskList.get(instructionNum).markAsUndone();
            System.out.println("Okay, I've marked this task as not done yet:");
            System.out.println(this.taskList.get(instructionNum));
            break;
        case "delete":
            Task removedTask = this.taskList.get(instructionNum);
            this.taskList.remove(instructionNum);
            System.out.println("Okay, I've removed this task:");
            System.out.println(removedTask);
            System.out.println("You now have " + this.taskList.size() + " tasks in your list.");
            break;
        }

        System.out.println(BREAK_LINES);
    }

    /**
     * Handles adding of instructions to taskList
     * @throws DukeException User input provided is incomplete / does not match required format.
     */
    public void addTaskInstructionHandler() throws DukeException {
        System.out.println(BREAK_LINES);
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
                throw new DukeException("Oops! To create an event, please format your input in " +
                        "this manner:\n<Event Name> /at <Event Date and Time of Occurrence>");
            }
            newTask = new Event(this.restOfInputString.substring(0, slashIndex - 1),
                    this.restOfInputString.substring(slashIndex + 4));
            break;
        case "deadline":
            if (divider == null || !divider.equals("/by")) {
                throw new DukeException("Oops! To create a deadline, please format your input in " +
                        "this manner:\n<Deadline Name> /by <Deadline>");
            }
            newTask = new Deadline(this.restOfInputString.substring(0, slashIndex - 1),
                    this.restOfInputString.substring(slashIndex + 4));
            break;
        }

        taskList.add(newTask);
        System.out.println("Got it, i've added this task to your list:\n  " + newTask);
        System.out.println("You now have " + taskList.size() + " tasks in your list.");
        System.out.println(BREAK_LINES);
    }

}
