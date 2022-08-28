package duke.models;

import duke.exceptions.DukeException;
import duke.handlers.DukeCommand;
import duke.handlers.DukeCommandMap;

/**
 * Represents a parser for user input strings.
 *
 * @author Zhu Yuanxi
 */
public class Parser {
    public static final DukeCommandMap commandMap = new DukeCommandMap();

    /**
     * Parses the user input string as a command object.
     *
     * @param userInput The user input containing the command representation to be parsed.
     * @return The command object represented by the user input string.
     * @throws DukeException If the user input does not follow the required patterns.
     */
    public DukeCommand parseCommand(String userInput) throws DukeException {
        String[] input = userInput.split("\\s+", 2);
        String keyword = input[0].toLowerCase();
        return commandMap.getCommand(keyword);
    }

    /**
     * Parses the user input string.
     *
     * @param userInput The user input containing the content of the command.
     * @return The content of the command represented by the user input string.
     * @throws DukeException If the user input does not follow the required patterns.
     */
    public String parseContent(String userInput) throws DukeException {
        String[] input = userInput.split("\\s+", 2);
        return input.length < 2 ? "" : input[1];
    }
}
