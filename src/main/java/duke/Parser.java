package duke;

import duke.command.*;
import duke.exception.*;
import duke.task.*;

public class Parser {

    public static Command parse(String input) throws DukeException{
        if (input.equals("bye")) {
            return new ExitCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        String[] words = input.split(" ");
        // mark keyword: checks the box of an item in the list
        if (words[0].equals("mark")) {
            String numString = words[1];
            int idx = Integer.parseInt(numString) - 1;
            return new MarkCommand(idx);
        }
        // unmark keyword: unchecks the box of an item in the list
        else if (words[0].equals("unmark")) {
            String numString = words[1];
            int idx = Integer.parseInt(numString) - 1;
            return new UnmarkCommand(idx);
        }
        // to do keyword: create new to do item
        else if (words[0].equals("todo")) {
            if (words.length == 1) {
                throw new EmptyTodoException();
            } else {
                String desc = input.split("todo ", 2)[1];
                return new AddCommand(new Todo(desc));
            }
        }
        // deadline keyword: create new deadline item
        else if (words[0].equals("deadline")) {
            String[] dl = input.split(" /by ", 2);
            if (dl.length <= 1) {
                throw new EmptyDateException();
            }
            String desc = dl[0].split("deadline ", 2)[1];
            return new AddCommand(new Deadline(desc, dl[1]));
        }
        // event keyword: create new event item
        else if (words[0].equals("event")) {
            String[] ev = input.split(" /at ", 2);
            if (ev.length <= 1) {
                throw new EmptyDateException();
            }
            String desc = ev[0].split("event ", 2)[1];
            return new AddCommand(new Event(desc, ev[1]));
        }
        // delete keyword: remove task from list
        else if (words[0].equals("delete")) {
            String numString = words[1];
            int idx = Integer.parseInt(numString) - 1;
            return new DeleteCommand(idx);
        } else {
            throw new UnknownCommandException();
        }
    }
}
