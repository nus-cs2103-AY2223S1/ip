package commands;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses a command using regex and returns the corresponding command, value, flag and additionalValues
 */
public class CommandParser {
    private static final Pattern commandParser =
            Pattern.compile("^([a-zA-Z]+)(?:\\s([^/]+))?(?:\\s/([a-zA-Z]+))?(?:\\s(.*))?$");

    public Command getCommand(String input) throws DukeException {
        String firstWord = input.split(" ", 2)[0];
        Command command;
        try {
            command = Command.valueOf(firstWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeException("No Such command!");
        }
        return command;
    }

    public List<String> parseCommand(String command) {
        Matcher matcher = CommandParser.commandParser.matcher(command);
        matcher.find();
        String function = matcher.group(1);
        String value = matcher.group(2);
        String flag = matcher.group(3);
        String additionalValue = matcher.group(4);
        List<String> result = new ArrayList<>();
        result.add(value);
        result.add(flag);
        result.add(additionalValue);
        return result;
    }
}
