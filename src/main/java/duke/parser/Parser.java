package duke.parser;

import duke.Duke;
import duke.command.*;
import duke.exception.DukeException;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
         * @param keyword a string indicating the user's intentions
         */
        Keyword(String keyword) {
            this.value = keyword;
        }

        /**
         * Finds and returns a Keyword based on the user's input Keyword.
         *
         * @param inputKeyword the user's input Keyword
         * @return the corresponding Keyword related to a Command
         */
        public static Keyword getKeyword(String inputKeyword) throws DukeException {
            for (Keyword k : Keyword.values()) {
                if (inputKeyword.toLowerCase().equals(k.value)) {
                    return k;
                }
            }
            throw new DukeException("\tInvalid command: " + inputKeyword);
        }
    }

    /**
     * Returns the corresponding Command with respect to the user's input.
     *
     * @param userInput the user's input
     * @return a Command associated with the user's input
     */
    public static Command parse(String userInput) throws DukeException {

        String[] input = userInput.split(" ", 2);

        String inputKeyword = input[0];

        Keyword keyword = Keyword.getKeyword(inputKeyword);

        String[] description;

        String[] inputArray;

        Command command;

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
            inputArray = userInput.split(" ", 0);
            if (inputArray.length < 2) {
                throw new DukeException("Please put in a description!");
            }
            command = new AddCommand(new ToDo(input[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case DEADLINE:
            inputArray = userInput.split(" ", 0);

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
            command = new AddCommand(new Deadline(description[0], description[1]));
            Parser.updateLastUserInput(userInput);
            break;
        case EVENT:
            inputArray = userInput.split(" ", 0);
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
            command = null;
            break;
        }
        return command;
    }

    public static boolean isValid(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static void updateLastUserInput(String input) {
        Parser.lastUserInput = input;
    }

    public static String getLastUserInput() {
        return Parser.lastUserInput;
    }
}