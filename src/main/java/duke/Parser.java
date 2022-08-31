package duke;

import java.util.Arrays;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private static String[] keywords = {"bye", "list", "mark", "unmark", "todo", "deadline", "event", "delete", "find"};

    public Parser() {
    }

    ;

    /**
     * Returns Keyword extracted from user input.
     * If user input is not a keyword, exception is thrown.
     *
     * @param keyword Keyword input from user.
     * @return Keyword.
     * @throws DukeException if user input is not a keyword.
     */
    public Duke.Keyword getKeyword(String keyword) throws DukeException {
        if (Arrays.asList(keywords).contains(keyword)) {
            return Duke.Keyword.valueOf(keyword.toUpperCase());
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public int getIndex(String text) {
        return Integer.parseInt(text);
    }

    /**
     * Returns Deadline object from user input.
     *
     * @param nextLine Input from user.
     * @return Deadline object.
     */
    public Deadline createDeadline(String nextLine) {
        String[] section = nextLine.split(" /by ");
        return new Deadline(section[0], false, section[1]);
    }

    /**
     * Returns Event object from user input.
     *
     * @param nextLine Input from user.
     * @return Event object.
     */
    public Event createEvent(String nextLine) {
        String[] sections = nextLine.split(" /at ");
        return new Event(sections[0], false, sections[1]);
    }
}