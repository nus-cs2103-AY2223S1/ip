package duke;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Deals with making sense of the user command and converting input into a format understandable by the Duke program.
 */
public class Parser {
    private static final String SPACE_SEPARATOR = " ";
    private static final String EMPTY_STRING = "";

    private static final HashMap<String, String> SHORTCUTS = initialiseShortcuts();

    private static HashMap<String, String> initialiseShortcuts() {
        HashMap<String, String> shortcuts = new HashMap<>();
        shortcuts.put("b", "bye");
        shortcuts.put("l", "list");
        shortcuts.put("h", "help");
        shortcuts.put("f", "find");
        shortcuts.put("m", "mark");
        shortcuts.put("um", "unmark");
        shortcuts.put("del", "delete");
        shortcuts.put("t", "todo");
        shortcuts.put("d", "deadline");
        shortcuts.put("e", "event");
        return shortcuts;
    }

    private static void fillNullElementsWithEmptyString(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = EMPTY_STRING;
            }
        }
    }

    /**
     * Parses an input string into a format that Duke can understand (An array of String).
     *
     * @param input A user input string.
     * @return A String array containing the parsed user input.
     * @throws BannedDukeCharacterException If input contains a '|' character.
     * @throws InvalidDukeInputException If input does not contain a recognised Duke command.
     * @throws MissingDukeInputException If input contains a command that is lacking supporting information.
     */
    public static String[] parseInput(String input)
            throws BannedDukeCharacterException,
            InvalidDukeInputException,
            MissingDukeInputException {
        // Remove spaces at the end of the input.
        String trimmedInput = input.trim();

        checkForBannedCharacters(trimmedInput);
        String[] parsedOutput = splitInputIntoArray(trimmedInput);
        checkForBadInput(parsedOutput);
        return parsedOutput;
    }

    private static void checkForBannedCharacters(String input) throws BannedDukeCharacterException {
        String bannedCharacter = "|";
        if (input.contains(bannedCharacter)) {
            throw new BannedDukeCharacterException(bannedCharacter);
        }
    }

    private static String[] splitInputIntoArray(String input) {
        // There are at most 6 parameters in an input. Status of task is assumed to be undone.
        int parsedArraySize = 6;
        String[] parsedOutput = new String[parsedArraySize];
        /*
         Splits the input into 2 halves, one containing the command itself and the other containing the
         rest of the string.
         */
        String[] commandSplitInput = input.split(SPACE_SEPARATOR, 2);
        parsedOutput[0] = parseCommand(commandSplitInput[0]);
        parsedOutput[1] = commandSplitInput.length != 1 ? commandSplitInput[1] : EMPTY_STRING;

        String cmd = parsedOutput[0];
        switch (cmd) {
        case "deadline":
        case "event":
            String deadlineSeparator = " /by ";
            String eventSeparator = " /at ";
            String splitWord = cmd.equals("deadline") ? deadlineSeparator : eventSeparator;
            String[] str2 = parsedOutput[1].split(splitWord, 2);
            if (str2.length == 1) {
                break;
            }
            String description = str2[0];
            String[] dateTime = Arrays.copyOf(str2[1].split(SPACE_SEPARATOR), 4);
            parsedOutput[1] = description;
            parsedOutput[2] = dateTime[0];
            parsedOutput[3] = dateTime[1];
            parsedOutput[4] = dateTime[2];
            parsedOutput[5] = dateTime[3];
            break;
        default:
            // Do nothing
        }
        fillNullElementsWithEmptyString(parsedOutput);
        return parsedOutput;
    }

    private static String parseCommand(String s) {
        String parsedCommand = s.toLowerCase();
        if (SHORTCUTS.containsKey(parsedCommand)) {
            parsedCommand = SHORTCUTS.get(parsedCommand);
        }
        return parsedCommand;
    }

    private static void checkForBadInput(String[] parsedOutput)
            throws InvalidDukeInputException, MissingDukeInputException {
        String cmd = parsedOutput[0];
        int minimumArgumentsNeeded;
        switch (cmd) {
        case "bye":
        case "list":
        case "help":
            minimumArgumentsNeeded = 1;
            break;
        case "find":
        case "mark":
        case "unmark":
        case "delete":
        case "todo":
            minimumArgumentsNeeded = 2;
            break;
        case "deadline":
            minimumArgumentsNeeded = 4;
            break;
        case "event":
            minimumArgumentsNeeded = 6;
            break;
        default:
            throw new InvalidDukeInputException();
        }

        int minimumArrayIndexNeededToBeFilled = minimumArgumentsNeeded - 1;
        boolean hasMissingArguments = parsedOutput[minimumArrayIndexNeededToBeFilled].equals(EMPTY_STRING);
        if (hasMissingArguments) {
            throw new MissingDukeInputException(cmd);
        }
    }
}
