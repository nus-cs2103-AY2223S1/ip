package duke.task;

import java.util.Date;

import duke.DukeException;
import duke.util.Parser.CommandName;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create new Task
     * 
     * @param description Task Description
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Constructor to create new Task with isDone
     * 
     * @param description Task Description
     * @param isDone      Whether the task is done or not
     */
    public Task(String description, boolean isDone) {
        this.description = description.trim();
        this.isDone = isDone;
    }

    /**
     * Checks if the input string contains a description
     * 
     * @param splitInput String split by " " and limited to 2
     * @throws DukeException if no description is present
     */
    public static void checkDescription(String[] splitInput) throws DukeException {
        if (splitInput.length == 1) {
            throw new DukeException("Please enter the description when creating new task!");
        }
    }

    /**
     * Factory method to create new Task
     * 
     * @param input String containing task type, description and deadline/timing if
     *              needed
     * @return new Task
     * @throws DukeException if invalid task type or wrong parameters
     */
    public static Task createTask(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);

        switch (CommandName.valueOf(splitInput[0])) {
        case todo:
            checkDescription(splitInput);
            return Todo.createTodo(splitInput[1]);
        case deadline:
            checkDescription(splitInput);
            return Deadline.createDeadline(splitInput[1]);
        case event:
            checkDescription(splitInput);
            return Event.createEvent(splitInput[1]);
        default:
            throw new DukeException("I'm sorry but I don't know what that means.");
        }
    }

    /**
     * Returns a string representation of whether task is done or not.
     * 
     * @return "X" if task is done, " " if not done
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks task as done and prints info to user.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + this);
    }

    /**
     * Marks task as not done and prints info to user.
     */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + this);
    }

    /**
     * Parses a date string and returns a Date object.
     * 
     * @param input Date string
     * @return Date object
     * @throws DukeException if date string has invalid format
     */
    public static Date parseDate(String input) throws DukeException {
        SimpleDateFormat formatter;
        if (input.indexOf("/") != -1) {
            formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        } else if (input.indexOf("-") != -1) {
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        } else if (input.indexOf(",") != -1) {
            formatter = new SimpleDateFormat("EEE, MMM d, hh:mm a");
        } else {
            throw new DukeException(
                    "Please make sure your date is in `dd/MM/yyyy HH:mm` or `dd-MM-yyyy HH:mm` format.");
        }
        try {
            return formatter.parse(input);
        } catch (ParseException e) {
            throw new DukeException(
                    "Please make sure your date is in `dd/MM/yyyy HH:mm` or `dd-MM-yyyy HH:mm` format.");
        }
    }

    public static String formatDate(Date date) {
        SimpleDateFormat standardDateFormat = new SimpleDateFormat("EEE, MMM d, hh:mm a");
        return standardDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string represetation of task when saving to file
     * 
     * @return String representation of task when saving to file
     */
    public abstract String getFileString();

}
