package duke;

import java.util.Arrays;

/**
 * Deals with making sense of the user command and converting input into a format understandable by the Duke program.
 */
public class Parser {
    private static final String SPACE_SEPARATOR = " ";
    private static final String EMPTY_STRING = "";

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
        String BANNED_CHARACTER = "|";
        if (input.contains(BANNED_CHARACTER)) {
            throw new BannedDukeCharacterException(BANNED_CHARACTER);
        }
    }

    private static String[] splitInputIntoArray(String input) {
        // There are at most 6 parameters in an input. Status of task is assumed to be undone.
        final int PARSED_ARRAY_SIZE = 6;
        String[] parsedOutput = new String[PARSED_ARRAY_SIZE];
        String[] str = input.split(SPACE_SEPARATOR, 2);
        parsedOutput[0] = str[0];
        parsedOutput[1] = str.length != 1 ? str[1] : EMPTY_STRING;

        String cmd = parsedOutput[0];
        switch (cmd) {
        case "deadline":
        case "event":
            final String DEADLINE_SEPARATOR = " /by ";
            final String EVENT_SEPARATOR = " /at ";
            String splitWord = cmd.equals("deadline") ? DEADLINE_SEPARATOR : EVENT_SEPARATOR;
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
        default:
            // Do nothing
        }
        fillNullElementsWithEmptyString(parsedOutput);
        return parsedOutput;
    }

    private static void checkForBadInput(String[] parsedOutput)
            throws InvalidDukeInputException, MissingDukeInputException {
        String cmd = parsedOutput[0];
        int minimumArgumentsNeeded;
        switch (cmd) {
            case "bye":
            case "list":
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
