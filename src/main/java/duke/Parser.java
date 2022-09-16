package duke;

import java.util.Scanner;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.SearchCommand;
import duke.command.ShowListCommand;
import duke.command.UnmarkCommand;

/**
 * Parser to parse the input string and determine its command.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Parser {

    //Exception Messages
    private static final String NO_DESC_TODO = "OOPS!!! Description of the todo can't be empty.";
    private static final String NO_DESC_DEADLINE = "OOPS!!! Description of the deadline can't be empty.";
    private static final String NO_DESC_EVENT = "OOPS!!! Description of the event can't be empty.";
    private static final String INVALID_DEADLINE_FORMAT =
            "OOPS!!! Please create the deadline using the following format:\n"
            + "deadline <description> /by <date>";
    private static final String INVALID_EVENT_FORMAT =
            "OOPS!!! Please create the event using the following format:\n"
            + "event <description> /at <date>";
    private static final String NO_INDEX = "OOPS!!! Index of the task to be marked/unmarked/deleted can't be empty.";
    private static final String NO_TARGET = "OOPS!!! Keyword of task to be found can't be empty.";
    private static final String UNKNOWN_COMMAND = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    /**
     * Processes the input string and returns its corresponding Command.
     *
     * @param command Input string that denotes a command for the chatbot.
     * @return Command that the string is referring to.
     * @throws DukeException If the input string is invalid and does not represent a valid command.
     */
    public static Command parse(String command) throws DukeException {
        Scanner sc = new Scanner(command);

        if (sc.hasNext("mark")) {
            return handleMark(sc);
        } else if (sc.hasNext("unmark")) {
            return handleUnmark(sc);
        } else if (sc.hasNext("deadline")) {
            return handleDeadline(sc);
        } else if (sc.hasNext("event")) {
            return handleEvent(sc);
        } else if (sc.hasNext("todo")) {
            return handleTodo(sc);
        } else if (sc.hasNext("delete")) {
            return handleDelete(sc);
        } else if (sc.hasNext("find")) {
            return handleFind(sc);
        } else if (sc.hasNext("search")) {
            return handleSearch(sc);
        } else if (command.equals("bye")) {
            return handleBye(sc, command);
        } else if (command.equals("list")) {
            return handleList(sc, command);
        } else {
            throw new DukeException(UNKNOWN_COMMAND);
        }
    }

    private static Command handleMark(Scanner sc) throws DukeException {
        String mark = sc.next();
        assert (mark.equals("mark"));

        if (!sc.hasNextInt()) {
            throw new DukeException(NO_INDEX);
        } else {
            int i = sc.nextInt();
            sc.close();
            return new MarkCommand(i);
        }
    }

    private static Command handleUnmark(Scanner sc) throws DukeException {
        String unmark = sc.next();
        assert (unmark.equals("unmark"));
        if (!sc.hasNextInt()) {
            throw new DukeException(NO_INDEX);
        } else {
            int i = sc.nextInt();
            sc.close();
            return new UnmarkCommand(i);
        }
    }

    private static Command handleDeadline(Scanner sc) throws DukeException {
        sc.useDelimiter("deadline\\s*|\\s*/by\\s*");
        if (!sc.hasNext()) {
            throw new DukeException(NO_DESC_DEADLINE);
        }
        String description = sc.next();
        if (!sc.hasNext()) {
            throw new DukeException(INVALID_DEADLINE_FORMAT);
        }
        String by = sc.next();
        sc.close();
        return new AddCommand("deadline", description, by);
    }

    private static Command handleEvent(Scanner sc) throws DukeException {
        sc.useDelimiter("event\\s*|\\s*/at\\s*");
        if (!sc.hasNext()) {
            throw new DukeException(NO_DESC_EVENT);
        }
        String description = sc.next();
        if (!sc.hasNext()) {
            throw new DukeException(INVALID_EVENT_FORMAT);
        }
        String at = sc.next();
        sc.close();
        return new AddCommand("event", description, at);
    }

    private static Command handleTodo(Scanner sc) throws DukeException {
        sc.useDelimiter("todo\\s*");
        if (!sc.hasNext()) {
            throw new DukeException(NO_DESC_TODO);
        }
        String description = sc.next();
        sc.close();
        return new AddCommand("todo", description, null);
    }

    private static Command handleDelete(Scanner sc) throws DukeException {
        sc.useDelimiter("delete\\s*");
        if (!sc.hasNextInt()) {
            throw new DukeException(NO_INDEX);
        } else {
            int i = sc.nextInt();
            sc.close();
            return new DeleteCommand(i);
        }
    }

    private static Command handleFind(Scanner sc) throws DukeException {
        sc.useDelimiter("find\\s*");
        if (!sc.hasNext()) {
            throw new DukeException(NO_TARGET);
        } else {
            String s = sc.next();
            sc.close();
            return new FindCommand(s);
        }
    }

    private static Command handleSearch(Scanner sc) throws DukeException {
        sc.useDelimiter("search\\s*");
        if (!sc.hasNext()) {
            throw new DukeException(NO_TARGET);
        } else {
            String s = sc.next();
            sc.close();
            return new SearchCommand(s);
        }
    }

    private static Command handleBye(Scanner sc, String command) {
        assert (command.equals("bye"));
        sc.close();
        return new ExitCommand();
    }

    private static Command handleList(Scanner sc, String command) {
        assert (command.equals("list"));
        sc.close();
        return new ShowListCommand();
    }
}
