package byu;

import commands.*;

import exceptions.DukeException;
import exceptions.EmptyDescriptionException;
import exceptions.InvalidInstructionException;

import java.util.Locale;

import task.Deadline;
import task.ToDo;
import task.Event;

/**
 * Represents a parser, that interprets a given string and translates them into commands.
 */
public class Parser {

    /**
     * Returns the Command instance corresponding to the string.
     *
     * @param fullCommand the string to be parsed.
     */
    public static Command parse(String fullCommand) {
        Command c = new NextCommand();
        try {
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
            }
        } catch (DukeException e) {
            System.out.print(e.getMessage());
            c = new NextCommand();
        }
        return c;
    }

    private static Instruction getInstruction(String fullCommand) throws InvalidInstructionException {
        try {
            String instruction = fullCommand.split(" ")[0];
            Instruction instruct = Instruction.valueOf(instruction.toUpperCase(Locale.ROOT));
            return instruct;
        } catch (IllegalArgumentException e) {
            throw new InvalidInstructionException();
        }
    }

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

    private static MarkCommand prepareMark(String d) {
        //try {
            Integer index = Integer.valueOf(d);
            return new MarkCommand(index);
        //} catch (NumberFormatException e) {
            // throw
        //}
    }

    private static UnmarkCommand prepareUnmark(String d) {
        //try {
            Integer index = Integer.valueOf(d);
            return new UnmarkCommand(index);
        //} catch (NumberFormatException e) {
            // throw
        //}
    }

    private static AddCommand prepareEvent(String d) throws EmptyDescriptionException {
        String[] substrings = d.split(" /at ");
        if (substrings.length == 1) {
            throw new EmptyDescriptionException("Event");
        } else {
            Event e = new Event(substrings[0], substrings[1]);
            return new AddCommand(e);
        }
    }
    private static AddCommand prepareDeadline(String d) throws EmptyDescriptionException {
        String[] substrings = d.split(" /by ");
        if (substrings.length == 1) {
            throw new EmptyDescriptionException("Deadline");
        } else {
            Deadline deadline = new Deadline(substrings[0], substrings[1]);
            return new AddCommand(deadline);
        }
    }

    private static AddCommand prepareToDo(String d) {
        ToDo t = new ToDo(d);
        return new AddCommand(t);
    }

    private static DeleteCommand prepareDelete(String d) {
        //try {
            Integer index = Integer.valueOf(d);
            return new DeleteCommand(index);
        //} catch (NumberFormatException e) {
            // throw
        //}
    }
    
}
