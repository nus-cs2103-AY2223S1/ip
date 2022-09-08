package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDateException;
import duke.exception.EmptyTodoException;
import duke.exception.InvalidDateException;
import duke.exception.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * The Parser class helps to make sense of user commands.
 *
 * @author Edric Yeo
 */
public class Parser {

    /**
     * Method that takes in a String of user input and returns a specific
     * type of command to achieve the task specified.
     *
     * @param input The line of text inputted by the user.
     * @return A Command that performs the task specified by the user.
     * @throws DukeException If input is invalid.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        String[] words = input.split(" ");
        if (words[0].equals("mark")) {
            int idx = Integer.parseInt(words[1]) - 1;
            assert idx >= 0;
            return new MarkCommand(idx);
        } else if (words[0].equals("unmark")) {
            int idx = Integer.parseInt(words[1]) - 1;
            assert idx >= 0;
            return new UnmarkCommand(idx);
        } else if (words[0].equals("todo")) {
            String desc = getTodoDescription(input);
            return new AddCommand(new Todo(desc));
        } else if (words[0].equals("deadline")) {
            String[] splitStringByBy = input.split(" /by ", 2);
            if (splitStringByBy.length <= 1) {
                throw new EmptyDateException();
            }
            String formattedDate = formatDate(splitStringByBy[1]);
            String desc = splitStringByBy[0].split("deadline ", 2)[1];
            return new AddCommand(new Deadline(desc, formattedDate));
        } else if (words[0].equals("event")) {
            String[] splitStringByAt = input.split(" /at ", 2);
            if (splitStringByAt.length <= 1) {
                throw new EmptyDateException();
            }
            String formattedDate = formatDate(splitStringByAt[1]);
            String desc = splitStringByAt[0].split("event ", 2)[1];
            return new AddCommand(new Event(desc, formattedDate));
        } else if (words[0].equals("delete")) {
            int idx = Integer.parseInt(words[1]) - 1;
            assert idx >= 0;
            return new DeleteCommand(idx);
        } else if (words[0].equals("find")) {
            String[] find = input.split("find ", 2);
            String keyword = find[1];
            return new FindCommand(keyword);
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Method that formats the date field, given the input and keyword
     *
     * @param date A String containing the date field.
     * @return A String representing the formatted date.
     * @throws DukeException If input is invalid.
     */
    public static String formatDate(String date) throws DukeException {
        String formattedDate = "";
        try {
            LocalDate unformattedDate = LocalDate.parse(date);
            formattedDate = unformattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
        return formattedDate;
    }

    /**
     * Method that returns the description field of a Todo, given the input.
     *
     * @param input The command entered by the user.
     * @return A String representing the description of the Todo.
     * @throws DukeException If input is invalid.
     */
    public static String getTodoDescription(String input) throws DukeException {
        if (input.split(" ").length == 1) {
            throw new EmptyTodoException();
        }
        return input.split("todo ", 2)[1];
    }
}
