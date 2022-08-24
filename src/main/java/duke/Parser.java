package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
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

public class Parser {

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
            String[] dl = input.split(" /by ", 2);
            if (dl.length <= 1) {
                throw new EmptyDateException();
            }
            String desc = dl[0].split("deadline ", 2)[1];
            return new AddCommand(new Deadline(desc, dl[1]));
        } else if (words[0].equals("event")) {
            // event keyword: create new event item
            String[] ev = input.split(" /at ", 2);
            if (ev.length <= 1) {
                throw new EmptyDateException();
            }
            String desc = ev[0].split("event ", 2)[1];
            return new AddCommand(new Event(desc, ev[1]));
        } else if (words[0].equals("delete")) {
            // delete keyword: remove task from list
            String numString = words[1];
            int idx = Integer.parseInt(numString) - 1;
            return new DeleteCommand(idx);
        } else {
            throw new UnknownCommandException();
        }
    }
}
