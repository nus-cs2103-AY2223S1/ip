package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.GreetCommand;
import duke.command.IdleCommand;
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
     * @param userInput the input to be parsed
     * @return the parsed command
     */
    public static Command parse(String userInput) {
        String[] substrings = userInput.split(" ", 2);
        boolean isSingleWordCommand = substrings.length == 1;

        if (userInput.equals("greet")) {
            return new GreetCommand();
        } else if (userInput.equals("bye")) {
            return new ExitCommand();
        } else if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("mark")) {
            // when user enters mark and a number
            // mark the corresponding task as done
            if (isSingleWordCommand) {
                throw new DukeException("Please specify which task to mark.");
            }
            int index = Integer.parseInt(substrings[1]) - 1;
            return new MarkCommand(index);
        } else if (userInput.startsWith("unmark")) {
            // when user enters unmark and a number
            // mark the corresponding task as not done
            if (isSingleWordCommand) {
                throw new DukeException("Please specify which task to unmark.");
            }
            int index = Integer.parseInt(substrings[1]) - 1;
            return new UnmarkCommand(index);
        } else if (userInput.startsWith("delete")) {
            // when user enters delete and a number
            // delete the corresponding task from the list
            if (isSingleWordCommand) {
                throw new DukeException("Please specify which task to delete.");
            }
            int index = Integer.parseInt(substrings[1]) - 1;
            return new DeleteCommand(index);
        } else if (userInput.startsWith("find")) {
            // when user enters find and a keyword
            // search for that keyword in the list
            // and print out the result
            if (isSingleWordCommand) {
                throw new DukeException("Please specify what keyword to search for.");
            }
            String keyword = substrings[1];
            return new FindCommand(keyword);
        } else {
            // add user input to the list
            // check what type of task it is
            String description = substrings[1].split("/")[0].trim();
            if (isSingleWordCommand) {
                throw new DukeException("The description of a task cannot be empty.");
            }
            if (userInput.startsWith("todo")) {
                Todo todo = new Todo(description);
                return new AddCommand(todo);
            } else if (userInput.startsWith("deadline")) {
                String by = substrings[1].split("/")[1].split(" ", 2)[1];
                Deadline deadline = new Deadline(description, by);
                return new AddCommand(deadline);
            } else if (userInput.startsWith("event")) {
                String at = substrings[1].split("/")[1].split(" ", 2)[1];
                Event event = new Event(description, at);
                return new AddCommand(event);
            } else {
                return new IdleCommand();
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
