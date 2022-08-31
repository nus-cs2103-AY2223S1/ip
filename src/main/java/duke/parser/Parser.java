package duke.parser;

import java.time.LocalDate;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ByeCommand;
import duke.command.DateCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private final static char UNDONE_STATUS = 'U';

    /**
     * Parses the user command.
     *
     * @param input The input from user.
     * @return The corresponding command class.
     * @throws DukeException Throw exception when there is an invalid user input.
     */
    public static Command parseCommand(String input) throws DukeException{
        String[] arguments =  input.trim().split(" ", 2);
        String commandType = arguments[0];
            if ("bye".equalsIgnoreCase(commandType)) {
               return exit();
            } else if ("list".equalsIgnoreCase(commandType)) {
                return listAllTasks();
            } else if ("unmark".equalsIgnoreCase(commandType)) {
                return unMarkTask(arguments);
            } else if ("mark".equalsIgnoreCase(commandType)) {
                 return markTask(arguments);
            } else if ("todo".equalsIgnoreCase(commandType)) {
               return addToDo(arguments);
            } else if ("deadline".equalsIgnoreCase(commandType)) {
               return addDeadline(arguments);
            } else if ("event".equalsIgnoreCase(commandType)) {
              return addEvent(arguments);
            } else if ("date".equalsIgnoreCase(commandType)) {
               return listTasksOnDate(arguments);
            } else if ("delete".equalsIgnoreCase(commandType)) {
               return deleteTask(arguments);
            } else {
                throw new InvalidInputException();
            }
    }

    private static Command addToDo (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        String toDo = arguments[1];
        return new AddCommand(new ToDo(toDo, UNDONE_STATUS));
    }

    private static Command addDeadline (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        String[] inputs = arguments[1].split("/");
        String description = inputs[0];
        LocalDate date = LocalDate.parse(extractDateByKeyword("by",inputs[1]));
        return new AddCommand(new Deadline(description, date, UNDONE_STATUS));
    }

    private static Command addEvent (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        String[] inputs = arguments[1].split("/");
        String description = inputs[0];
        LocalDate date = LocalDate.parse(extractDateByKeyword("at",inputs[1]));
        return new AddCommand(new Event(description, date, UNDONE_STATUS));
    }

    private static Command deleteTask (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        int taskNo = Integer.parseInt(arguments[1]);
        return new DeleteCommand(taskNo);
    }

    private static Command markTask (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        int taskNo = Integer.parseInt(arguments[1]);
        return new MarkCommand(taskNo);
    }

    private static Command unMarkTask (String[] arguments) throws EmptyDescriptionException {
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        int taskNo = Integer.parseInt(arguments[1]);
        return new UnMarkCommand(taskNo);
    }

    private static Command listAllTasks ()  {
        return new ListCommand();
    }

    private static Command listTasksOnDate (String[] arguments) throws EmptyDescriptionException{
        if (arguments.length == 1) {
            throw new EmptyDescriptionException();
        }
        LocalDate date = LocalDate.parse(arguments[1]);
        return new DateCommand(date);
    }

    private static Command exit ()  {
        return new ByeCommand();
    }
    private static String extractDateByKeyword (String keyword, String text) {
        String[] args = text.split(keyword);
        String date = args[1].trim();
        return date;
    }
}
