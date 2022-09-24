package cinnamon.Handler;
import cinnamon.Exception.DukeException;
import cinnamon.command.Command;
import cinnamon.command.List;
import cinnamon.command.FindTask;
import cinnamon.command.DateFilter;
import cinnamon.command.DeleteTask;
import cinnamon.command.FindTag;
import cinnamon.command.Tag;
import cinnamon.command.RemoveTag;
import cinnamon.command.Mark;
import cinnamon.command.Unmark;
import cinnamon.command.PrintTag;
import cinnamon.command.Bye;
import cinnamon.command.add.AddEvent;
import cinnamon.command.add.AddDeadline;
import cinnamon.command.add.AddTodo;

/**
 * Parser that parses input into the program
 */
public class Parser {


    /**
     * @param input that is being read
     * @throws DukeException when input is invalid
     */
    public static Command parse(String input) throws DukeException {

        assert(input.length() != 0);
        if (input.startsWith("#")) {
            return new FindTag(input);
        } else if (input.startsWith("list")) {
            return new List();
        } else if (input.startsWith("mark")) {
            return new Mark(input);
        } else if (input.startsWith("unmark")) {
            return new Unmark(input);
        } else if (input.startsWith("todo")) {
            return new AddTodo(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadline(input);
        } else if (input.startsWith("event")) {
            return new AddEvent(input);
        } else if (input.startsWith("delete")) {
            return new DeleteTask(input);
        } else if (input.startsWith("find")) {
            return new FindTask(input);
        } else if (input.startsWith("bye")) {
            return new Bye();
        } else if (input.startsWith("dateFilter")) {
            return new DateFilter(input);
        } else if (input.startsWith("tag")) {
            return new Tag(input);
        } else if (input.startsWith("print")) {
            return new PrintTag(input);
        } else if (input.startsWith("remove")) {
            return new RemoveTag(input);
        }
        throw new DukeException("☹ OOPS!!! This is not a valid command.");
    }
}
