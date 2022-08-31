package byu;

import java.util.Locale;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.NextCommand;
import commands.UnmarkCommand;
import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidDescriptionException;
import exceptions.InvalidInstructionException;
import task.Deadline;
import task.Event;
import task.ToDo;

/**
 * Represents a parser, that interprets a given string and translates them into commands.
 */
public class Parser {

    /**
     * Returns the Command instance corresponding to the string.
     *
     * @param fullCommand the string to be parsed.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command c;
        Instruction i = getInstruction(fullCommand);
        String description = getDescription(i, fullCommand);
        switch (i) {
        case MARK:
            c = prepareMark(description);
            break;
        case UNMARK:
            c = prepareUnmark(description);
            break;
        case DEADLINE:
            c = prepareDeadline(description);
            break;
        case EVENT:
            c = prepareEvent(description);
            break;
        case TODO:
            c = prepareToDo(description);
            break;
        case DELETE:
            c = prepareDelete(description);
            break;
        case BYE:
            c = new ByeCommand();
            break;
        case LIST:
            c = new ListCommand();
            break;
        case FIND:
            c = new FindCommand(description);
            break;
        default:
            c = new NextCommand();
        }
        return c;
    }


    /**
     * Returns the instruction in the command.
     *
     * @param fullCommand the string to be parsed.
     * @throws InvalidInstructionException if the instruction is invalid.
     */
    private static Instruction getInstruction(String fullCommand) throws InvalidInstructionException {
        try {
            String instruction = fullCommand.split(" ")[0];
            return Instruction.valueOf(instruction.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new InvalidInstructionException();
        }
    }

    /**
     * Returns the description in the command.
     *
     * @param i           the instruction in the command.
     * @param fullCommand the string to be parsed.
     * @throws EmptyDescriptionException if the description is empty.
     */
    private static String getDescription(Instruction i, String fullCommand) throws EmptyDescriptionException {
        switch (i) {
        case MARK:
        case UNMARK:
        case DEADLINE:
        case TODO:
        case EVENT:
        case DELETE:
        case FIND:
            String[] substrings = fullCommand.split(" ");
            if (substrings.length == 1) {
                throw new EmptyDescriptionException(i.name());
            } else {
                return fullCommand.substring(substrings[0].length() + 1);
            }
        default:
            return "";
        }
    }

    /**
     * Returns a MarkCommand with the given description.
     *
     * @param d description of the command indicating index to be marked.
     * @return a MarkCommand with the given description.
     * @throws InvalidDescriptionException if the description cannot be converted to an integer.
     */
    private static MarkCommand prepareMark(String d) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(d);
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("index");
        }
    }

    /**
     * Returns an UnmarkCommand with the given description.
     *
     * @param d description of the command indicating index to be unmarked.
     * @return an UnmarkCommand with the given description.
     * @throws InvalidDescriptionException if the description cannot be converted to an integer.
     */
    private static UnmarkCommand prepareUnmark(String d) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(d);
            return new UnmarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("index");
        }
    }

    /**
     * Returns an AddCommand instantiated with an Event.
     *
     * @param d description of the command indicating name and date of event.
     * @return an AddCommand instantiated with an Event.
     * @throws InvalidDescriptionException if the date cannot be interpreted.
     */
    private static AddCommand prepareEvent(String d) throws InvalidDescriptionException {
        String[] substrings = d.split(" /at ");
        if (substrings.length == 1) {
            throw new InvalidDescriptionException("event");
        } else {
            Event e = new Event(substrings[0], substrings[1]);
            return new AddCommand(e);
        }
    }

    /**
     * Returns an AddCommand instantiated with a Deadline.
     *
     * @param d description of the AddCommand indicating name and date of deadline.
     * @return an AddCommand instantiated with a Deadline.
     * @throws InvalidDescriptionException if the date cannot be interpreted.
     */
    private static AddCommand prepareDeadline(String d) throws InvalidDescriptionException {
        String[] substrings = d.split(" /by ");
        if (substrings.length == 1) {
            throw new InvalidDescriptionException("deadline");
        } else {
            Deadline deadline = new Deadline(substrings[0], substrings[1]);
            return new AddCommand(deadline);
        }
    }

    /**
     * Returns an AddCommand instantiated with a ToDo.
     *
     * @param d description of the command indicating name of todo.
     * @return an AddCommand instantiated with a ToDo.
     */
    private static AddCommand prepareToDo(String d) {
        ToDo t = new ToDo(d);
        return new AddCommand(t);
    }

    /**
     * Returns a DeleteCommand with the given description.
     *
     * @param d description of the command indicating index to be deleted.
     * @return a DeleteCommand with the given description.
     * @throws InvalidDescriptionException if the description cannot be converted to an integer.
     */
    private static DeleteCommand prepareDelete(String d) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(d);
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException("index");
        }
    }

}
