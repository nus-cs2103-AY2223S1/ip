package myduke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnMarkCommand;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.ToDo;

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
            int index = Integer.parseInt(numString) - 1;
            return new MarkCommand(index);
        } else if (words[0].equals("unmark")) {
            // unmark keyword: unchecks the box of an item in the list
            String numString = words[1];
            int index = Integer.parseInt(numString) - 1;
            return new UnMarkCommand(index);
        } else if (words[0].equals("todo")) {
            // to do keyword: create new to do item
            if (words.length == 1) {
                throw new DukeException("Description cannot be empty!");
            } else {
                String desc = input.split("todo ", 2)[1];
                return new AddCommand(new ToDo(desc, false));
            }
        } else if (words[0].equals("deadline")) {
            // deadline keyword: create new deadline item
            String[] splitStringByBy = input.split(" /by ", 2);
            if (splitStringByBy.length <= 1) {
                throw new DukeException("The date cannot be empty!");
            }
            LocalDateTime date = LocalDateTime.now();
            try {
                date = LocalDateTime.parse(splitStringByBy[1]);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date format! Use a YYYY-MM-DD format!");
            }
            String desc = splitStringByBy[0].split("deadline ", 2)[1];
            return new AddCommand(new Deadline(desc, false, date));
        } else if (words[0].equals("event")) {
            // event keyword: create new event item
            String[] splitStringByAt = input.split(" /at ", 2);
            if (splitStringByAt.length <= 1) {
                throw new DukeException("The date cannot be empty!");
            }
            String desc = splitStringByAt[0].split("event ", 2)[1];
            String date = splitStringByAt[1];
            return new AddCommand(new Event(desc, false, date));
        } else if (words[0].equals("delete")) {
            // delete keyword: remove task from list
            String numString = words[1];
            int index = Integer.parseInt(numString) - 1;
            return new DeleteCommand(index);
        } else if (words[0].equals("find")) {
            // find keyword: find matching tasks
            String[] find = input.split("find ", 2);
            String keyword = find[1];
            return new FindCommand(keyword);
        } else {
            throw new DukeException("Invalid Command entered!");
        }
    }
}
