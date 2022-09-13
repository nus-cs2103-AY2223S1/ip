package byu;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
 *  A parser that interprets a given string and translates them into commands.
 */
public class Parser {

    /**
     * Returns the Command instance corresponding to the string.
     *
     * @param fullCommand the string to be parsed.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command command;
        Instruction instruction = getInstruction(fullCommand);
        String description = getDescription(instruction, fullCommand);
        switch (instruction) {
        case MARK:
            command = new MarkCommand(getDescriptionAsIntegerValue(Instruction.MARK, description));
            break;
        case UNMARK:
            command = new UnmarkCommand(getDescriptionAsIntegerValue(Instruction.UNMARK, description));
            break;
        case DEADLINE:
            command = prepareDeadline(description);
            break;
        case EVENT:
            command = prepareEvent(description);
            break;
        case TODO:
            command = prepareToDo(description);
            break;
        case DELETE:
            command = new DeleteCommand(getDescriptionAsIntegerValue(Instruction.DELETE, description));
            break;
        case BYE:
            command = new ByeCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case FIND:
            command = new FindCommand(description);
            break;
        default:
            command = new NextCommand();
        }
        return command;
    }


    /**
     * Returns the instruction in the command.
     *
     * @param fullCommand the string to be parsed.
     * @throws InvalidInstructionException if the instruction is invalid.
     */
    private static Instruction getInstruction(String fullCommand) throws InvalidInstructionException {
        String firstWord = fullCommand.split(" ")[0];
        try {
            return Instruction.valueOf(firstWord.toUpperCase(Locale.ROOT));
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
            String[] words = fullCommand.split(" ");
            boolean hasDescription = words.length > 1;
            if (!hasDescription) {
                throw new EmptyDescriptionException(i);
            }
            int indexOfDescription = words[0].length() + 1;
            String description = fullCommand.substring(indexOfDescription);
            return description;
        default:
            return "";
        }
    }

    /**
     * Returns an integer from the given description.
     *
     * @param d description of the command indicating index of a task.
     * @return an integer representing the index of a task.
     * @throws InvalidDescriptionException if the description cannot be converted to an integer.
     */
    private static int getDescriptionAsIntegerValue(Instruction i, String d) throws InvalidDescriptionException {
        try {
            int index = Integer.parseInt(d);
            return index;
        } catch (NumberFormatException e) {
            throw new InvalidDescriptionException(i);
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
            throw new InvalidDescriptionException(Instruction.EVENT);
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
            throw new InvalidDescriptionException(Instruction.DEADLINE);
        }
        LocalDateTime dateTime;
        try {
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy h:mma");
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            dateTime = LocalDateTime.parse(substrings[1], inputFormatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDescriptionException(Instruction.DEADLINE);
        }
        Deadline deadline = new Deadline(substrings[0], dateTime);
        return new AddCommand(deadline);
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

}
