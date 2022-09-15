package duke.parser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.format.DateTimeParseException;

import duke.Duke;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.HelpFormatter;


import duke.commands.*;
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


    private TaskList taskList;
    private String keyword;
    private String restOfInputString;
    private CommandLineParser parser = new DefaultParser();
    private HelpFormatter formatter = new HelpFormatter();
    private CommandLine cmd;


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
     * @throws DukeException if instruction execution fails
     */
    public Command execute() throws DukeException {
        if (this.keyword == null) {
            throw new DukeException("Error: Parser has not been loaded with an instruction yet!");
        }

        if (this.keyword.equals("")) {
            throw new DukeException("I can't do anything based off a blank instruction!");
        }

        switch (this.keyword) {
        case "list":
            return this.listInstructionHandler();
        case "bye":
            return this.byeInstructionHandler();
        case "reminders":
            return this.reminderInstructionHandler();
        case "help":
            return this.helpInstructionHandler();
        case "find":
            return this.findInstructionHandler();
        case "mark":
            // Intentional Fallthrough
        case "unmark":
            // Intentional Fallthrough
        case "delete":
            return this.numericalInstructionHandler();
        case "todo":
            // Intentional fallthrough
        case "event":
            // Intentional fallthrough
        case "deadline":
            return this.addTaskInstructionHandler();
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
     * @return List command for execution.
     */
    public Command listInstructionHandler() {
        return new ListCommand();
    }

    /**
     * Handles a bye instruction by exiting Duke.
     * @return Bye command for execution.
     */
    public Command byeInstructionHandler() {
        return new ByeCommand();
    }

    /**
     * Handles a reminder command by printing user's reminders to the screen.
     * @return Reminder command for execution.
     */
    public Command reminderInstructionHandler() {
        return new ReminderCommand();
    }

    /**
     * Handles a help command by printing user help to the screen.
     * @return Help command for execution.
     */
    public Command helpInstructionHandler() {
        return new HelpCommand();
    }

    /**
     * Handles numerical instructions that act on an indexed element in taskList
     *
     * @throws DukeException Index is invalid
     */
    public Command numericalInstructionHandler() throws DukeException {
        StringWriter out = new StringWriter();
        PrintWriter pw = new PrintWriter(out);
        Options numericalInstructionOptions = new Options();
        Option targetNum = new Option("t", "target", true, "target task num");
        targetNum.setRequired(true);
        numericalInstructionOptions.addOption(targetNum);
        String[] splitRest = this.restOfInputString.split(" ");
        try {
            cmd = parser.parse(numericalInstructionOptions, splitRest);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            formatter.printHelp(pw, 80, this.keyword + " -t [target]",
                    "", numericalInstructionOptions,
                    formatter.getLeftPadding(), formatter.getDescPadding(), "");
            pw.flush();
            errorMessage += "\n" + out.toString();
            throw new DukeException(errorMessage);
        }

        int instructionNum = Integer.valueOf(cmd.getOptionValue("target")) - 1;


        if (instructionNum >= this.taskList.getSize() || instructionNum < 0) {
            throw new DukeException("Invalid index provided. Try again?");
        }

        switch (this.keyword) {
        case "mark":
            return new MarkCommand(instructionNum);
        case "unmark":
            return new UnmarkCommand(instructionNum);
        case "delete":
            return new DeleteCommand(instructionNum);
        default:
            throw new DukeException("Command not recognised. Try again?");
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

        StringWriter out = new StringWriter();
        PrintWriter pw = new PrintWriter(out);
        Options numericalInstructionOptions = new Options();

        // Set command-line options for add task instruction
        Option taskDesc = new Option("d", "description", true, "new task description");
        taskDesc.setArgs(Option.UNLIMITED_VALUES);
        taskDesc.setRequired(true);

        Option taskDeadline = new Option("by", "bydeadline", true, "new task deadline");
        taskDeadline.setArgs(2);

        Option taskTime = new Option("at", "attime", true, "new task occurrence time");
        taskTime.setArgs(2);

        numericalInstructionOptions.addOption(taskDesc);
        numericalInstructionOptions.addOption(taskDeadline);
        numericalInstructionOptions.addOption(taskTime);

        String[] splitRest = this.restOfInputString.split(" ");
        try {
            cmd = parser.parse(numericalInstructionOptions, splitRest);
        } catch (Exception e) {
            String errorMessage = e.getMessage();
            formatter.printHelp(pw, 80, this.keyword + " -d [description] -by [DD-MM-YYYY HH:MM] -at [DD-MM-YYYY HH:MM]",
                    "", numericalInstructionOptions,
                    formatter.getLeftPadding(), formatter.getDescPadding(), "");
            pw.flush();
            errorMessage += "\n" + out.toString();
            throw new DukeException(errorMessage);
        }

        Task newTask = createNewTask(keyword, cmd);

        return new AddCommand(newTask);
    }

    /**
     * Abstract logic to create a task based on keyword.
     * @param keyword Task keyword of input string
     * @param cmd CommandLine object to parse user's command string
     * @return Task object according to keyword
     * @throws DukeException If input string provided has invalid values
     */
    public Task createNewTask(String keyword, CommandLine cmd) throws DukeException {
        Task newTask;
        switch (this.keyword) {
        case "todo":
            for (String s: cmd.getOptionValues("description")) {
                System.out.println(s);
            }
            newTask = new Todo(String.join(" ", cmd.getOptionValues("description")));
            break;
        case "event":
            if (!cmd.hasOption("at")) {
                throw new DukeException("Oops! To create an event, please format your input in "
                        + "this manner:\nevent -d [description] -at [DD-MM-YYYY HH:MM]");
            }
            try {
                newTask = new Event(String.join(" ", cmd.getOptionValues("description")),
                        String.join(" ", cmd.getOptionValues("at")));
            } catch (DateTimeParseException e) {
                throw new DukeException("Oops! Events must have a date of occurrence, formatted "
                        + "as dd-mm-yyyy hh:mm.");
            }
            break;
        case "deadline":
            if (!cmd.hasOption("by")) {
                throw new DukeException("Oops! To create a deadline, please format your input in "
                        + "this manner:\ndeadline -d [description] -by [DD-MM-YYYY HH:MM]");
            }
            try {
                newTask = new Deadline(String.join(" ", cmd.getOptionValues("description")),
                        String.join(" ", cmd.getOptionValues("by")));
            } catch (DateTimeParseException e) {
                throw new DukeException("Oops! Deadlines must have a valid deadline, "
                        + "formatted as dd-mm-yyyy hh:mm.");
            }
            break;
        default:
            throw new DukeException("Oops! An error occurred when creating a new task.");
        }

        assert (newTask != null) : "addTaskInstructionHandler cannot return a null task.";
        
        return newTask;

    }

}
