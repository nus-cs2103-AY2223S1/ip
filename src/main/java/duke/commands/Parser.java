package duke.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidSyntaxException;

/**
 * Parses a command using regex and returns the corresponding command, value, flag and additionalValues
 */
public class Parser {
    private static final Pattern commandParser =
            Pattern.compile("^([a-zA-Z]+)(?:\\s([^/|]+))?(?:\\s/([a-zA-Z]+))?(?:\\s([^|]*))?$");

    public Command getCommand(String input) throws DukeException {
        String firstWord = input.split(" ", 2)[0];
        Command command;
        try {
            command = Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidSyntaxException(String.format("\"%s\" is not a valid command!", firstWord));
        }
        return command;
    }

    /**
     * Parses the command input using the regex and splits the command into
     * command, value, flag and additionalValue.
     * @param command the user input.
     * @return a List containing the values of the parsed command.
     */
    public List<String> parseCommand(String command) throws DukeException {
        Matcher matcher = Parser.commandParser.matcher(command);
        try {
            matcher.find();
            String value = matcher.group(2);
            String flag = matcher.group(3);
            String additionalValue = matcher.group(4);
            List<String> results = new ArrayList<>();
            results.add(value);
            results.add(flag);
            results.add(additionalValue);
            return results;
        } catch (IllegalStateException e) {
            throw new InvalidSyntaxException(String.format("\"%s\" is not a valid command.", command));
        }
    }
}
