package dukeprogram.parser;

import java.util.Arrays;
import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.command.AccessTasksCommand;
import exceptions.IncompleteCommandException;
import exceptions.InvalidCommandException;

/**
 * Parser will parse a command string in any given command context.
 */
public class Parser {

    private Duke duke;

    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses the user's input into usable elements to trigger commands
     * @param userInput the command of the user
     */
    public void parse(String userInput) throws IncompleteCommandException {
        Iterator<String> elements = convertToIterator(userInput);

        if (!elements.hasNext()) {
            throw new IncompleteCommandException();
        }

        String thisElement = elements.next();

        try {
            switch (thisElement) {
            case "tasks":
                new AccessTasksCommand(duke).parse(elements);
                break;

            case "loans":
                duke.sendMessage("We have not implemented this command yet");
                break;

            default:
                duke.sendMessage(String.format("\"%s\" is not a valid command!", thisElement));
            }
        } catch (InvalidCommandException | IncompleteCommandException e) {
            duke.sendMessage(e.getMessage());
        }
    }

    /**
     * Converts the provided string into an iterator split by whitespaces
     * @param input the input string given
     * @return an iterator of the input, separated by whitespaces
     */
    public Iterator<String> convertToIterator(String input) {
        return Arrays.stream(input.split(" ")).iterator();
    }
}
