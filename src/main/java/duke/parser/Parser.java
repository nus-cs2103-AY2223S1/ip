package duke.parser;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.Commands;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.MassDeleteCommand;
import duke.command.MassUnMarkCommand;
import duke.command.UnMarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyArgumentException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskNumberException;
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
     * Parses the input from the user and returns a corresponding command.
     *
     * @param input The input from the CLI.
     * @return The command to be executed.
     * @throws DukeException If the input String has an error.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputs = initialInputHandler(input);
        Command command;
        String keyPhrase = inputs[0];
        String argument = inputs[1];
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
            command = deleteHandler(argument);
            break;
        case "find":
            command = new FindCommand(findHandler(argument));
            break;
        case "massunmark":
            command = new MassUnMarkCommand(massCommandHandler(argument));
            break;
        default:
            throw new InvalidCommandException(keyPhrase);
        }
        return command;
    }

    /**
     * Handles the mass command instruction.
     *
     * @param input The input of the mass command.
     * @return The command corresponding to the users input.
     * @throws DukeException If there is an exception.
     */
    public static Commands massCommandHandler(String input) throws DukeException {
        if (input.isBlank()) {
            throw new EmptyArgumentException(Commands.All);
        }
        switch(input) {
        case "deadline":
            return Commands.Deadline;
        case "event":
            return Commands.Event;
        case "todo":
            return Commands.ToDo;
        case "all":
            return Commands.All;
        default:
            throw new InvalidArgumentException(Commands.All);
        }
    }

    /**
     * Handles the initial input from the user.
     *
     * @param input The user's input.
     * @return An array containting the commands and arguments.
     */
    public static String[] initialInputHandler(String input) {
        String[] phrases = new String[2];
        String[] inputs = input.split(" ", 2);
        String argument = "";
        if (inputs.length == 2) {
            phrases[1] = inputs[1];
        } else {
            phrases[1] = "";
        }
        phrases[0] = inputs[0];
        return phrases;
    }

    /**
     * Handles the parsing of the todo string.
     *
     * @param information The details of the todo task.
     * @return An instance of ToDo which contains the information.
     * @throws EmptyArgumentException If no details of the task is provided.
     */
    public static ToDo toDoHandler(String information) throws EmptyArgumentException {
        if (information.isBlank()) {
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
        if (information.isBlank()) {
            throw new EmptyArgumentException(Commands.Deadline);
        }
        if (!information.contains("/by")) {
            throw new InvalidArgumentException(Commands.Deadline);
        }
        String[] stringArr = information.split(" /by ", 2);
        if (stringArr.length == 1 || stringArr[0].isBlank()) {
            throw new EmptyArgumentException(Commands.Deadline);
        }
        if (stringArr.length == 2 && stringArr[1].isBlank()) {
            throw new EmptyArgumentException(Commands.Deadline);
        }
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
        if (information.isBlank()) {
            throw new EmptyArgumentException(Commands.Event);
        }
        if (!information.contains("/at")) {
            throw new InvalidArgumentException(Commands.Event);
        }
        String[] stringArr = information.split(" /at ", 2);
        if (stringArr.length == 1 || stringArr[0].isBlank()) {
            throw new EmptyArgumentException(Commands.Event);
        }
        if (stringArr.length == 2 && stringArr[1].isBlank()) {
            throw new EmptyArgumentException(Commands.Event);
        }
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
        if (information.isBlank()) {
            throw new EmptyArgumentException(Commands.Mark);
        }
        if (!information.chars().allMatch(Character :: isDigit)) {
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
    public static Command deleteHandler(String information) throws DukeException {
        if (information.isEmpty()) {
            throw new EmptyArgumentException(Commands.Delete);
        }
        if (information.chars().allMatch(Character :: isDigit)) {
            int index = Integer.parseInt(information) - 1;
            if (index < 0) {
                throw new InvalidTaskNumberException();
            }
            return new DeleteCommand(index);
        } else if (information.equals("completed")) {
            return new MassDeleteCommand();
        } else {
            throw new InvalidArgumentException(Commands.Delete);
        }
    }

    /**
     * Handles the find command.
     *
     * @param information The keyword to search.
     * @return The keyword to search.
     * @throws DukeException If the information provided is invalid.
     */
    public static String findHandler(String information) throws DukeException {
        if (information.isBlank()) {
            throw new EmptyArgumentException(Commands.Find);
        }
        return information;
    }

}
