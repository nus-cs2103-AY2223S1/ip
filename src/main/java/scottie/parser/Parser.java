package scottie.parser;

import scottie.instructions.Command;
import scottie.instructions.Instruction;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final String FLAG_REGEX = " /(\\w+) ";

    public static Instruction parse(String userInput) throws InvalidCommandException {
        String[] splitUserInput = userInput.split(" ", 2);
        Command command = Command.fromName(splitUserInput[0]);
        if (command == null) {
            throw new InvalidCommandException(splitUserInput[0]);
        }
        if (splitUserInput.length == 1) {
            // Instruction has no arguments
            return Instruction.of(command, null, Map.of());
        }
        String[] arguments = splitUserInput[1].split(FLAG_REGEX);
        String mainArgument = arguments[0];
        Matcher flagMatcher = Pattern.compile(FLAG_REGEX).matcher(splitUserInput[1]);
        Map<String, String> flagArgumentsMap = new HashMap<>();
        int i = 1;
        while (flagMatcher.find()) {
            flagArgumentsMap.put(flagMatcher.group(1), arguments[i++]);
        }
        return Instruction.of(command, mainArgument, flagArgumentsMap);
    }
}
