package duke.parser;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

/**
 * The Parser class which helps Duke parse
 * the inputs from the user.
 *
 * @author Leong Jia Hao Daniel
 */
public class Parser {

    /**
     * The main parsing command which handles the raw input string from
     * the user.
     *
     * @param input The input from the CLI.
     * @return The command to be executed.
     * @throws DukeException If the input String has an error.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ",2);
        String keyPhrase = inputArray[0];
        String argument = "";
        Command command;
        if (inputArray.length == 2) {
            argument = inputArray[1];
        }
        switch(keyPhrase) {
            case "bye":
                command = new ExitCommand();
                break;
            case "list":
                command = new ListCommand();
                break;
            case "mark":
                command = new MarkCommand(markHandler(argument));
                break;
            case "unmark":
                command = new UnMarkCommand(markHandler(argument));
                break;
            case "todo":
                ToDo todo = toDoHandler(argument);
                command = new AddCommand(todo);
                break;
            case "deadline":
                Deadline deadline = deadlineHandler(argument);
                command = new AddCommand(deadline);
                break;
            case "event":
                Event event = eventHandler(argument);
                command = new AddCommand(event);
                break;
            case "delete":
                command = new DeleteCommand(deleteHandler(argument));
                break;
            case "find":
                command = new FindCommand(findHandler(argument));
                break;
            default:
                throw new InvalidCommandException(keyPhrase);
        }
        return command;
    }

    /**
     * Handles the parsing of the todo string.
     *
     * @param information The details of the todo task.
     * @return An instance of ToDo which contains the information.
     * @throws EmptyArgumentException If no details of the task is provided.
     */
    public static ToDo toDoHandler(String information) throws EmptyArgumentException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.ToDo);
        }
        ToDo todo = new ToDo(information);
        return todo;
    }

    /**
     * Handles the parsing of the deadline string.
     *
     * @param information The details of the deadline task.
     * @return An instance of Deadline which contains the information.
     * @throws DukeException If the information provided is in the wrong format.
     */
    public static Deadline deadlineHandler(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Deadline);
        }
        if (!information.contains("/by")) {
            throw new InvalidArgumentException(Commands.Deadline);
        }
        String[] stringArr = information.split(" /by ",2);
        String[] dateTimeArr = stringArr[1].split(" ");
        Deadline deadline = null;
        try {
            deadline = new Deadline(stringArr[0], stringArr[1]);
        } catch (java.time.format.DateTimeParseException e) {
            throw new InvalidArgumentException(Commands.Date);
        }
        return deadline;
    }

    /**
     * Handles the parsing of the event string.
     *
     * @param information The details of the event task.
     * @return An instance of Event with the provided details.
     * @throws DukeException If the information provided is in the wrong format.
     */
    public static Event eventHandler(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Event);
        }
        if (!information.contains("/at")) {
            throw new InvalidArgumentException(Commands.Event);
        }
        String[] stringArr = information.split(" /at ",2);
        Event event = new Event(stringArr[0], stringArr[1]);
        return event;
    }

    /**
     * Handles the mark string.
     *
     * @param information The index of the task to be marked.
     * @return the index of the task in int.
     * @throws DukeException If the information provided is not an int.
     */
    public static int markHandler(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Mark);
        }
        if (!information.chars().allMatch( Character :: isDigit )) {
            throw new InvalidArgumentException(Commands.Mark);
        }
        int index = Integer.parseInt(information) - 1;
        if (index < 0) {
            throw new InvalidTaskNumberException();
        }
        return index;
    }

    /**
     * Handles the delete string.
     *
     * @param information The index of the task to be deleted.
     * @return The index of the task to be deleted in int.
     * @throws DukeException If the information provided is not a valid int.
     */
    public static int deleteHandler(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Delete);
        }
        if (!information.chars().allMatch( Character :: isDigit )) {
            throw new InvalidArgumentException(Commands.Delete);
        }
        int index = Integer.parseInt(information) - 1;
        if (index < 0) {
            throw new InvalidTaskNumberException();
        }
        return index;
    }

    public static String findHandler(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Find);
        }
        return information;
    }

}
