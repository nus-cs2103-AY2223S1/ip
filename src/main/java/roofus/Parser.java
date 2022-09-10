package roofus;

import roofus.command.ByeCommand;
import roofus.command.ClearCommand;
import roofus.command.Command;
import roofus.command.DeadlineCommand;
import roofus.command.DeleteCommand;
import roofus.command.EventCommand;
import roofus.command.FindCommand;
import roofus.command.ListCommand;
import roofus.command.MarkCommand;
import roofus.command.SortCommand;
import roofus.command.ToDoCommand;
import roofus.command.UnmarkCommand;
import roofus.task.Deadline;
import roofus.task.Event;
import roofus.task.ToDo;

/**
 * Represents a handler to convert user inputs
 * into commands that Roofus understands.
 */
public class Parser {
    /**
     * Represents a list of all valid commands.
     */
    private enum CommandList {
        BYE, LIST, TODO, EVENT, DEADLINE,
        MARK, UNMARK, DELETE, CLEAR, FIND, SORT
    }

    /**
     * Reads a user input and converts it
     * into a command.
     *
     * @param fullCommand String representing a user input.
     * @return Command
     * @throws RoofusException If user input is invalid.
     * @see Command
     */
    public static Command parse(String fullCommand) throws RoofusException {
        String[] split = fullCommand.split(" ", 2);
        String firstWord = split[0];
        try {
            CommandList command = CommandList.valueOf(firstWord.toUpperCase());
            switch (command) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case SORT:
                return new SortCommand();
            case DELETE:
                if (split.length < 2) {
                    throw new RoofusException("Huh?! Delete what?");
                }
                int index = Integer.parseInt(split[1]);
                return new DeleteCommand(index);
            case MARK:
                if (split.length < 2) {
                    throw new RoofusException("Huh?! Mark what?");
                }
                int index2 = Integer.parseInt(split[1]);
                return new MarkCommand(index2);
            case UNMARK:
                if (split.length < 2) {
                    throw new RoofusException("Huh?! Unmark what?");
                }
                int index3 = Integer.parseInt(split[1]);
                return new UnmarkCommand(index3);
            case TODO:
                if (split.length < 2) {
                    throw new RoofusException("To do what??");
                }
                return new ToDoCommand(new ToDo(split[1]));
            case DEADLINE:
                if (split.length < 2) {
                    throw new RoofusException("What deadline are you talking about?");
                }
                String details = split[1];
                String[] separate = details.split(" /by ", 2);
                if (separate.length < 2) {
                    throw new RoofusException("Wrong format for deadline!!");
                }
                return new DeadlineCommand(new Deadline(separate[0], separate[1]));
            case EVENT:
                if (split.length < 2) {
                    throw new RoofusException("What event are you talking about?");
                }
                String details2 = split[1];
                String[] separate2 = details2.split(" /at ", 2);
                if (separate2.length < 2) {
                    throw new RoofusException("Wrong format for event!!");
                }
                return new EventCommand(new Event(separate2[0], separate2[1]));
            case CLEAR:
                try {
                    return new ClearCommand();
                } catch (Exception err) {
                    throw new RoofusException(err.getMessage());
                }
            case FIND:
                if (split.length < 2 || split[1].isEmpty()) {
                    throw new RoofusException("Huh?! Find what?");
                }
                return new FindCommand(split[1]);
            default:
                assert false : command;
            }
        } catch (IllegalArgumentException | AssertionError err) {
            throw new RoofusException("\"" + split[0] + "\" is not a command!");
        }
        throw new RoofusException("Not sure what the error is");
    }
}
