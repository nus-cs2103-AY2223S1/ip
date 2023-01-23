package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * The Parser class contains the methods that handles user commands.
 */
public class Parser {
    /**
     * Initializes an instance of a Parser.
     */
    public Parser() {
    }

    /**
     * Parses the input of the user to an instance of a Command.
     *
     * @param input Input from user.
     * @return Command object.
     * @throws DukeException If user input is invalid.
     */
    public Command parse(String input) throws DukeException {
        String[] arg = input.split(" ", 2);
        String command = arg[0].toLowerCase();
        String commandArg = arg.length == 2 ? arg[1].trim() : "";
        switch (command) {
        case "list":
            checkNoArg(command, commandArg);
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseIntArg(commandArg));
        case "unmark":
            return new UnmarkCommand(parseIntArg(commandArg));
        case "delete":
            return new DeleteCommand(parseIntArg(commandArg));
        case "find":
            return new FindCommand(commandArg);
        case "todo":
            checkToDoArg(commandArg);
            return new ToDoCommand(new ToDo(commandArg));
        case "deadline":
            String[] deadlineArgs = getDeadlineArgs(commandArg);
            return new DeadlineCommand(new Deadline(deadlineArgs[0], parseDateArg(deadlineArgs[1])));
        case "event":
            String[] eventArgs = getEventArgs(commandArg);
            return new EventCommand(new Event(eventArgs[0], parseDateArg(eventArgs[1])));
        case "sort":
            boolean sortArgs = getSortArg(commandArg);
            return new SortCommand(sortArgs);
        case "bye":
            checkNoArg(command, commandArg);
            return new ByeCommand();
        default:
            throw new DukeException("Sorry, I don't understand what that means :(");
        }
    }

    private void checkToDoArg(String arg) throws DukeException {
        if (arg.equals("")) {
            throw new DukeException("A todo must contain a description.");
        }
    }

    private String[] getDeadlineArgs(String desc) throws DukeException {
        String[] res = desc.split("/by");
        trimArgs(res);
        checkDeadlineArgsValidity(res);
        return res;
    }

    private void checkDeadlineArgsValidity(String[] args) throws DukeException {
        if (args.length != 2 || args[0].equals("") || args[1].equals("")) {
            throw new DukeException("A deadline must contain a description and a due date.");
        }
    }

    private String[] getEventArgs(String desc) throws DukeException {
        String[] res = desc.split("/at");
        trimArgs(res);
        checkEventArgsValidity(res);
        return res;
    }

    private void checkEventArgsValidity(String[] args) throws DukeException {
        if (args.length != 2 || args[0].equals("") || args[1].equals("")) {
            throw new DukeException("An event must contain a description and a date.");
        }
    }


    private void trimArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
        }
    }

    private void checkNoArg(String command, String arg) throws DukeException {
        if (!arg.equals("")) {
            throw new DukeException(command + " " + "must not have any argument.");
        }
    }

    private boolean getSortArg(String option) throws DukeException {
        switch (option.toLowerCase()) {
        case "ascending":
            return true;
        case "descending":
            return false;
        default:
            throw new DukeException("Sort must be correctly specified: descending or ascending.");
        }
    }

    private int parseIntArg(String index) throws DukeException {
        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new DukeException("You must enter the index of the task.");
        }
    }

    private LocalDate parseDateArg(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("You have entered an invalid date format.");
        }
    }

}
