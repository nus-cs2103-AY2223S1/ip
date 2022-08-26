package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Encapsulation of parsing user inputs and records in the file.
 *
 * @author Sun Ruoxin
 */
public class Parser {

    /**
     * Parses user input to command.
     *
     * @param fullCommand the input to be parsed
     * @return the parsed command
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            // when user enters bye
            // exit programme
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            // when user enters list
            // display the current list
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            // when user enters mark and a number
            // mark the corresponding task as done
            if (fullCommand.split(" ", 2).length == 1) {
                throw new DukeException("Please specify which task to mark.");
            }
            int index = Integer.parseInt(fullCommand.split(" ", 2)[1]) - 1;
            return new MarkCommand(index);
        } else if (fullCommand.startsWith("unmark")) {
            // when user enters unmark and a number
            // mark the corresponding task as not done
            if (fullCommand.split(" ", 2).length == 1) {
                throw new DukeException("Please specify which task to unmark.");
            }
            int index = Integer.parseInt(fullCommand.split(" ", 2)[1]) - 1;
            return new UnmarkCommand(index);
        } else if (fullCommand.startsWith("delete")) {
            // when user enters delete and a number
            // delete the corresponding task from the list
            if (fullCommand.split(" ", 2).length == 1) {
                throw new DukeException("Please specify which task to delete.");
            }
            int index = Integer.parseInt(fullCommand.split(" ", 2)[1]) - 1;
            return new DeleteCommand(index);
        } else {
            // add user input to the list
            // check what type of task it is
            if (fullCommand.startsWith("todo")) {
                // the task is a todo
                if (fullCommand.split(" ", 2).length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                String description = fullCommand.split(" ", 2)[1].split("/")[0].trim();
                Todo todo = new Todo(description);
                return new AddCommand(todo);
            } else if (fullCommand.startsWith("deadline")) {
                // the task is a deadline
                if (fullCommand.split(" ", 2).length == 1) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String description = fullCommand.split(" ", 2)[1].split("/")[0].trim();
                String by = fullCommand.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];
                Deadline deadline = new Deadline(description, by);
                return new AddCommand(deadline);
            } else if (fullCommand.startsWith("event")) {
                // the task is an event
                if (fullCommand.split(" ", 2).length == 1) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                String description = fullCommand.split(" ", 2)[1].split("/")[0].trim();
                String at = fullCommand.split(" ", 2)[1].split("/")[1].split(" ", 2)[1];
                Event event = new Event(description, at);
                return new AddCommand(event);
            } else {
                throw new DukeException("I'm sorry, but I don't quite understand what that means.");
            }
        }
    }

    /**
     * Parses string record in the file to task to be stored.
     *
     * @param taskRecord the string record in the file
     * @return the task to be stored
     */
    public static Task toTask(String taskRecord) {
        if (taskRecord.startsWith("T")) {
            // the task is a todo
            String description = taskRecord.split("\\|", 3)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 3)[1].trim());
            return new Todo(description, checkStatus(status));
        } else if (taskRecord.startsWith("D")) {
            // the task is a deadline
            String description = taskRecord.split("\\|", 4)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 4)[2].trim());
            String by = taskRecord.split("\\|", 4)[3].trim();
            return new Deadline(description, checkStatus(status), parseDate(by));
        } else {
            // the task is an event
            String description = taskRecord.split("\\|", 4)[2].trim();
            int status = Integer.parseInt(taskRecord.split("\\|", 4)[2].trim());
            String at = taskRecord.split("\\|", 4)[3].trim();
            return new Event(description, checkStatus(status), at);
        }
    }

    /**
     * Checks whether the Task recorded in the file is marked as done.
     *
     * @param status integer representing the status of the Task
     * @return a boolean value representing whether the Task is marked as done
     */
    public static boolean checkStatus(int status) {
        // if status == 0, the task is not done yet
        // if status == 1, the task is done
        return status != 0;
    }

    /**
     * Parses the String record in the file to LocalDate to be stored.
     *
     * @param date the String in the record representing a date
     * @return the LocalDate to be stored in Task
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
