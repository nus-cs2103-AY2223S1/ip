package duke.parser;

import duke.command.*;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.ToDo;

import static java.lang.Integer.parseInt;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    enum Keyword {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find");

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
        public static Keyword getKeyword(String inputKeyword) {
            for (Keyword k : Keyword.values()) {
                if (inputKeyword.toLowerCase().equals(k.value)) {
                    return k;
                }
            }
            return null;
        }
    }

    /**
     * Returns the corresponding Command with respect to the user's input.
     *
     * @param userInput the user's input
     * @return a Command associated with the user's input
     */
    public static Command parse(String userInput) {
        String[] input = userInput.split(" ", 2);

        String inputKeyword = input[0];

        Keyword k = Keyword.getKeyword(inputKeyword);

        String[] description;

        switch (k) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(parseInt(input[1]));
            case UNMARK:
                return new UnmarkCommand(parseInt(input[1]));
            case TODO:
                return new AddCommand(new ToDo(input[1]));
            case DEADLINE:
                description = input[1].split(" /by ", 2);
                return new AddCommand(new Deadline(description[0], description[1]));
            case EVENT:
                description = input[1].split(" /at ", 2);
                String[] dates = description[1].split(" ");
                return new AddCommand(new Event(description[0], dates[0], dates[1]));
            case DELETE:
                return new DeleteCommand(parseInt(input[1]));
            case FIND:
                return new FindCommand(input[1]);
            default:
                return null;
        }
    }
}