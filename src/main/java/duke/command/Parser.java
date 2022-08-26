package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/** Deals with making sense of the user command */
public class Parser {

    /**
     * Returns a Command object.
     *
     * @param strInput String input from user.
     * @return Command object.
     */
    public static Command parse(String strInput) throws DukeException {
        if (strInput.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }
        if (strInput.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (isATodo(strInput)) {
            checkForNullTask(strInput.substring(4), "todo");
            checkForMultipleTasks(strInput.substring(4));
            return new AddCommand(new Todo(strInput.substring(5)));
        } else if (isADeadline(strInput)) {
            checkForNullTask(strInput.substring(8), "deadline");
            checkForMultipleTasks(strInput.substring(8));
            return new AddCommand(new Deadline(strInput.substring(9, strInput.indexOf("/") - 1),
                    strInput.substring(strInput.indexOf("/by") + 4), false));
        } else if (isAEvent(strInput)) {
            checkForNullTask(strInput.substring(5), "event");
            checkForMultipleTasks(strInput.substring(5));
            return new AddCommand(new Event(strInput.substring(6, strInput.indexOf("/") - 1),
                    strInput.substring(strInput.indexOf("/at") + 4), false));
        } else if (strInput.contains("delete") && strInput.substring(0, 6).equals("delete")) {
            return new DeleteCommand(Integer.parseInt(strInput.substring(7)));
        } else if (strInput.contains("unmark") && strInput.substring(0, 6).equals("unmark")) {
            return new MarkingCommand(false, Integer.parseInt(strInput.substring(7)));
        } else if (strInput.contains("mark") && strInput.substring(0, 4).equals("mark")) {
            return new MarkingCommand(true, Integer.parseInt(strInput.substring(5)));
        } else if (strInput.contains("find") && strInput.substring(0, 4).equals("find")) {
            return new FindCommand(strInput.substring(5));
        } else {
            throw new DukeException("Cannot recognise the command.");
        }
    }

    /**
     * Checks if there is task description.
     *
     * @param description task description.
     * @param typeOfTask todo, deadline, or event.
     * @throws DukeException if there is no task description.
     */
    public static void checkForNullTask(String description, String typeOfTask) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("There must be a task description for " + typeOfTask);
        }
        if (typeOfTask.equals("deadline")) {
            if (!description.contains("/by")) {
                throw new DukeException("There must be a deadline date for " + typeOfTask
                        + ". Format must be event <description> /by dd/mm/yyyy <time> with time being 24h.");
            }
        } else if (typeOfTask.equals("event")) {
            if (!description.contains("/at")) {
                throw new DukeException("There must be a date for " + typeOfTask
                        + ". Format must be event <description> /at dd/mm/yyyy <time> with time being 24h.");
            }
        }
    }

    /**
     * Checks if there are multiple tasks in one input.
     *
     * @param s user input.
     * @throws DukeException if there are multiple tasks in one input.
     */
    public static void checkForMultipleTasks(String s) throws DukeException {
        if (s.contains("todo") || s.contains("deadline") || s.contains("event")) {
            throw new DukeException("Multiple task detected.");
        }
    }

    /**
     * Check if is a todo task.
     *
     * @param s
     * @return boolean. True if is a todo task.
     */
    public static boolean isATodo(String s) {
        return s.contains("todo") && s.substring(0, 4).equals("todo");
    }

    /**
     * Check if is a task with deadline.
     *
     * @param s
     * @return boolean. True if is a deadline task.
     */
    public static boolean isADeadline(String s) {
        return s.contains("deadline") && s.substring(0, 8).equals("deadline");
    }

    /**
     * Check if is a event task.
     * @param s
     * @return boolean. True if is a event task.
     */
    public static boolean isAEvent(String s) {
        return s.contains("event") && s.substring(0, 5).equals("event");
    }
}
