/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke;

import duke.command.*;
import duke.task.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * class Parser to parse through commands input by user.
 */
public class Parser {

    /**
     * private enum class that stores Command Cases.
     */
    private enum CommandCases {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND
    }

    //formatter for date.
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" d/MM/uuuu");

    /**
     * public static method parse that parse input from user and returns Command for Duke to operate.
     * @param fullCommand
     * @return Command that user input.
     * @throws DukeException
     */
    public static Command parse(String fullCommand) throws DukeException {

        String[] com = fullCommand.split(" ", 2);

        CommandCases cs;

        try {
            cs = CommandCases.valueOf(com[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("No such command exist...");
        }

        try {
            if (com.length == 1) {
                switch (cs) {
                case LIST:
                    return new ListCommand();

                case MARK:
                    throw new DukeException("The index to mark cannot be left empty");

                case UNMARK:
                    throw new DukeException("The index to unmark cannot be left empty");

                case TODO:
                    throw new DukeException("The description of todo cannot be left empty");

                case DEADLINE:
                    throw new DukeException("The description of deadline cannot be left empty");

                case EVENT:
                    throw new DukeException("The description of event cannot be left empty");

                case DELETE:
                    throw new DukeException("The index to delete cannot be left empty");

                case BYE:
                    return new ByeCommand();

                case FIND:
                    throw new DukeException("The keyword to find cannot be left empty");

                default:
                    throw new DukeException("Invalid argument, please re enter a valid command...");
                }
            } else {
                switch (cs) {
                case LIST:
                    return new ListCommand();

                case MARK:
                    return new MarkCommand(Integer.parseInt(com[1]));

                case UNMARK:
                    return new UnmarkCommand(Integer.parseInt(com[1]));

                case TODO:
                    return new TodoCommand(com[1]);

                case DEADLINE:
                    String[] parse = com[1].split("/by", 2);
                    return new DeadlineCommand(parse[0], LocalDate.parse(parse[1], formatter));

                case EVENT:
                    String[] parse1 = com[1].split("/at", 2);
                    return new EventCommand(parse1[0], LocalDate.parse(parse1[1], formatter));

                case DELETE:
                    return new DeleteCommand(Integer.parseInt(com[1]));

                case FIND:
                    String[] keywordSplit = com[1].split("\\s");
                    return new FindCommand(keywordSplit);

                default:
                    throw new DukeException("No such command exist... please try again");

                }
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("hihi please provide date in dd/mm/yyyy format :)");
        }
    }
}
