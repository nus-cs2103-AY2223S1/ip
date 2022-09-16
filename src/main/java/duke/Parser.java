package duke;

import commands.*;
import dukeexceptions.DukeException;
import dukeexceptions.NoDescriptionException;
import dukeexceptions.NoSuchCommandException;

public class Parser {
    private enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, STATISTICS, HELP
    }

    /**
     * Returns Command that program is to execute from String input.
     *
     * @param input String to be converted to command.
     * @return Command for program to execute.
     * @throws DukeException If an illegal command is entered or not enough information was given.
     */
    public static Command parse(String input) throws DukeException {
        try {
            String[] inputSplit = input.split(" ", 2);
            Commands comm = Commands.valueOf(inputSplit[0].strip().toUpperCase());
            if (inputSplit.length == 1) {
                return handleSingleWordCommand(comm);
            } else {
                return handleMultipleWordCommand(comm, inputSplit[1]);
            }
        } catch (IllegalArgumentException e) {
            throw new NoSuchCommandException();
        }
    }

    /**
     * Returns Command that is to be executed if only a single command is entered.
     *
     * @param c Type of command entered by user.
     * @return Command to be executed by program.
     * @throws DukeException If an illegal command is entered or not enough information was given.
     */
    public static Command handleSingleWordCommand(Commands c) throws DukeException {
        switch (c) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case STATISTICS:
            return new StatisticsCommand();
        case HELP:
            return new HelpCommand();
        case UNMARK:
            throw new NoDescriptionException("unmark");
        case DEADLINE:
            throw new NoDescriptionException("deadline");
        case DELETE:
            throw new NoDescriptionException("delete");
        case EVENT:
            throw new NoDescriptionException("event");
        case MARK:
            throw new NoDescriptionException("mark");
        case TODO:
            throw new NoDescriptionException("todo");
        case FIND:
            throw new NoDescriptionException("find");
        default:
            throw new NoSuchCommandException();
        }
    }

    /**
     * Returns Command that is to be executed if a multiple field command is entered.
     *
     * @param c Type of command entered by user.
     * @param description The user input that contains the description of the command entered.
     * @return Command to be executed by program.
     * @throws DukeException If an illegal command is entered or not enough information was given.
     */
    public static Command handleMultipleWordCommand(Commands c, String description) throws DukeException {
        switch (c) {
        case UNMARK:
            int indUnmark = Integer.parseInt(description) - 1;
            return new UnMarkCommand(indUnmark);
        case MARK:
            int indMark = Integer.parseInt(description) - 1;
            return new MarkCommand(indMark);
        case DEADLINE:
            String[] descriptDate = description.split("/by", 2);
            return new DeadlineCommand(descriptDate[0], descriptDate[1]);
        case EVENT:
            String[] descriptTime = description.split("/at", 2);
            return new EventCommand(descriptTime[0], descriptTime[1]);
        case TODO:
            return new TodoCommand(description);
        case DELETE:
            int del = Integer.parseInt(description) - 1;
            return new DeleteCommand(del);
        case FIND:
            return new FindCommand(description);
        default:
            throw new NoSuchCommandException();
        }
    }
}

