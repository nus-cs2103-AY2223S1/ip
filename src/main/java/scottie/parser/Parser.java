package scottie.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import scottie.instructions.Instruction;
import scottie.instructions.InvalidCommandException;

/**
 * A utility class for parsing user input into Instructions.
 */
public class Parser {
    private static final String FLAG_REGEX = " /(\\w+) ";

    /**
     * Parses a string into an Instruction.
     *
     * @param userInput The string to parse.
     * @return The resulting Instruction.
     * @throws InvalidCommandException If the command name in the string is not a valid command.
     */
    public static Instruction parse(String userInput) throws InvalidCommandException {
        String[] splitUserInput = userInput.split(" ", 2);
        String commandName = splitUserInput[0];
        if (splitUserInput.length == 1) {
            // Instruction has no arguments
            return Instruction.of(commandName, null, Map.of());
        }
        String[] arguments = splitUserInput[1].split(FLAG_REGEX);
        String mainArgument = arguments[0];
        Matcher flagMatcher = Pattern.compile(FLAG_REGEX).matcher(splitUserInput[1]);
        Map<String, String> flagArgumentsMap = new HashMap<>();
        int i = 1;
        while (flagMatcher.find()) {
            flagArgumentsMap.put(flagMatcher.group(1), arguments[i++]);
        }
        return Instruction.of(commandName, mainArgument, flagArgumentsMap);
    }
}
