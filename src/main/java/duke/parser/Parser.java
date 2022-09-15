package duke.parser;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.UndoCommand;
import duke.exception.DukeException;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.ToDo;

import static java.lang.Integer.parseInt;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    private static String lastUserInput;

    enum Keyword {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find"),
        UNDO("undo");

        private String value;

        /**
         * A constructor for a Keyword.
         *
         * @param keyword a string indicating the user's intentions.
         */
        Keyword(String keyword) {
            this.value = keyword;
        }

        /**
         * Finds and returns a Keyword based on the user's input Keyword.
         *
         * @param inputKeyword The user's input Keyword.
         * @return The corresponding Keyword related to a Command.
         */
        public static Keyword getKeyword(String inputKeyword) throws DukeException {
            for (Keyword k : Keyword.values()) {
                if (inputKeyword.toLowerCase().equals(k.value)) {
                    return k;
                }
            }
            throw new DukeException("Don't understand command: " + inputKeyword);
        }
    }

    /**
     * Returns the corresponding Command with respect to the user's input.
     *
     * @param userInput The user's input.
     * @return A Command associated with the user's input.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] input = userInput.split(" ", 2);
        String inputKeyword = input[0];
        Keyword keyword = Keyword.getKeyword(inputKeyword);
        assert keyword != null : "Keyword should not be null";

        checkInputValidity(userInput, keyword);

        Command command;
        String[] description;
        String[] dates;

        switch (keyword) {
        case BYE:
            command = new ExitCommand();
            break;
        case LIST:
            command = new ListCommand();
            break;
        case MARK:
            command = new MarkCommand(parseInt(input[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case UNMARK:
            command = new UnmarkCommand(parseInt(input[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case TODO:
            command = new AddCommand(new ToDo(input[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case DEADLINE:
            description = input[1].split(" /by ", 2);
            command = new AddCommand(new Deadline(description[0], description[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case EVENT:
            description = input[1].split(" /at ", 2);
            dates = description[1].split(" ");
            command = new AddCommand(new Event(description[0], dates[0], dates[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case DELETE:
            command = new DeleteCommand(parseInt(input[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case FIND:
            command = new FindCommand(input[1]);
            break;
        case UNDO:
            command = new UndoCommand(lastUserInput);
            break;
        default:
            throw new DukeException("Don't understand command: " + inputKeyword);
        }
        assert command != null : "Command should not be null";
        return command;
    }

    /**
     * Checks the validity of the user input.
     *
     * @param userInput The user's input.
     * @param keyword The keyword given by the user.
     * @throws DukeException An exception related to the program.
     */
    public static void checkInputValidity(String userInput, Keyword keyword) throws DukeException {
        String[] input = userInput.split(" ", 2);
        String[] inputArray = userInput.split(" ", 0);
        String[] description;

        switch (keyword) {
        case MARK:
        case UNMARK:
        case DELETE:
            try {
                Integer.parseInt(input[1]);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please put in a valid task number!");
            }
            break;
        case TODO:
            if (inputArray.length < 2) {
                throw new DukeException("Please put in a description!");
            }
            break;
        case DEADLINE:
            if (!userInput.contains("/by")) {
                throw new DukeException("Please include '/by'!");
            } else if (inputArray[1].equals("/by")) {
                throw new DukeException("Please include a description!");
            } else if (inputArray[inputArray.length - 1].equals("/by")) {
                throw new DukeException("Please include a date!");
            }

            description = input[1].split(" /by ", 2);
            if (!Parser.isValid(description[1]) || description[1].split(" ").length > 1) {
                throw new DukeException("Invalid date given!");
            }
            break;
        case EVENT:
            if (!userInput.contains("/at")) {
                throw new DukeException("Please include '/at'!");
            } else if (inputArray[1].equals("/at")) {
                throw new DukeException("Please include a description!");
            } else if (inputArray[inputArray.length - 1].equals("/at")
                    || inputArray[inputArray.length - 2].equals("/at")) {
                throw new DukeException("Please include a start date and end date!");
            }

            description = input[1].split(" /at ", 2);
            String[] dates = description[1].split(" ");
            if (dates.length > 2) {
                throw new DukeException("Please give a proper start date and end date!");
            }
            if (!Parser.isValid(dates[0]) || !Parser.isValid(dates[1])) {
                throw new DukeException("Invalid start/end date!");
            }
            break;
        case FIND:
            if (input.length < 2) {
                throw new DukeException("Please include a description to search!");
            }
            break;
        case BYE:
        case LIST:
        case UNDO:
            if (input.length != 1) {
                throw new DukeException("Unexpected arguments detected!");
            }
            break;
        default:
            break;
        }
    }

    /**
     * Checks if input date is in the valid format.
     *
     * @param date The input date.
     * @return A boolean representing if the input date is in the valid format.
     */
    public static boolean isValid(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Updates the last user input given.
     *
     * @param input The last input given by the user.
     */
    public static void updateLastUserInput(String input) {
        Parser.lastUserInput = input;
    }
}
