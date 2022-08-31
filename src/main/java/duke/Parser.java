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
            // mark keyword: checks the box of an item in the list
            String numString = words[1];
            int idx = Integer.parseInt(numString) - 1;
            return new MarkCommand(idx);
        } else if (words[0].equals("unmark")) {
            // unmark keyword: unchecks the box of an item in the list
            String numString = words[1];
            int idx = Integer.parseInt(numString) - 1;
            return new UnmarkCommand(idx);
        } else if (words[0].equals("todo")) {
            // to do keyword: create new to do item
            if (words.length == 1) {
                throw new EmptyTodoException();
            } else {
                String desc = input.split("todo ", 2)[1];
                return new AddCommand(new Todo(desc));
            }
        } else if (words[0].equals("deadline")) {
            // deadline keyword: create new deadline item
            String[] splitStringByBy = input.split(" /by ", 2);
            if (splitStringByBy.length <= 1) {
                throw new EmptyDateException();
            }
            String formattedDate = "";
            try {
                LocalDate unformattedDate = LocalDate.parse(splitStringByBy[1]);
                formattedDate = unformattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date format! Use a YYYY-MM-DD format!");
            }
            String desc = splitStringByBy[0].split("deadline ", 2)[1];
            return new AddCommand(new Deadline(desc, formattedDate));
        } else if (words[0].equals("event")) {
            // event keyword: create new event item
            String[] splitStringByAt = input.split(" /at ", 2);
            if (splitStringByAt.length <= 1) {
                throw new EmptyDateException();
            }
            String formattedDate = "";
            try {
                LocalDate unformattedDate = LocalDate.parse(splitStringByAt[1]);
                formattedDate = unformattedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date format! Use a YYYY-MM-DD format!");
            }
            String desc = splitStringByAt[0].split("event ", 2)[1];
            return new AddCommand(new Event(desc, formattedDate));
        } else if (words[0].equals("delete")) {
            // delete keyword: remove task from list
            String numString = words[1];
            int idx = Integer.parseInt(numString) - 1;
            return new DeleteCommand(idx);
        } else if (words[0].equals("find")) {
            // find keyword: find matching tasks
            String[] find = input.split("find ", 2);
            String keyword = find[1];
            return new FindCommand(keyword);
        } else {
            throw new UnknownCommandException();
        }
    }
}
