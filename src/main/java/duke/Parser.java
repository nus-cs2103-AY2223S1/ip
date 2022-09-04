package duke;

import java.util.Arrays;

/**
 * Deals with making sense of the user command and converting input into a format understandable by the Duke program.
 */
public class Parser {
    private static void fillNullElementsWithEmptyString(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = "";
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
        return sortInputIntoArray(trimmedInput);
    }

    private static void checkForBannedCharacters(String input) throws BannedDukeCharacterException {
        String BANNED_CHARACTER = "|";
        if (input.contains(BANNED_CHARACTER)) {
            throw new BannedDukeCharacterException(BANNED_CHARACTER);
        }
    }

    private static String[] sortInputIntoArray(String input) {
        // There are at most 6 parameters in an input. Status of task is assumed to be undone.
        final int PARSED_ARRAY_SIZE = 6;
        String[] parsedOutput = new String[PARSED_ARRAY_SIZE];

        switch (input) {
        case "bye":
        case "list":
            parsedOutput[0] = input;
            return parsedOutput;
        default:
            String[] str = input.split(" ", 2);
            String cmd = str[0];
            parsedOutput[0] = cmd;
            checkForMissingInput(str, cmd);

            switch (cmd) {
            case "find":
            case "mark":
            case "unmark":
            case "delete":
            case "todo":
                parsedOutput[1] = str[1];
                return parsedOutput;
            case "deadline":
            case "event":
                String splitWord = cmd.equals("deadline") ? " /by " : " /at ";
                String[] str2 = str[1].split(splitWord);
                if (str2.length == 1) {
                    throw new MissingDukeInputException(cmd);
                }
                String description = str2[0];
                String[] dateTime = Arrays.copyOf(str2[1].split(" "), 4);
                fillNullElementsWithEmptyString(dateTime);
                parsedOutput[1] = description;
                parsedOutput[2] = dateTime[0];
                parsedOutput[3] = dateTime[1];
                parsedOutput[4] = dateTime[2];
                parsedOutput[5] = dateTime[3];
                return parsedOutput;
            default:
                throw new InvalidDukeInputException();
            }
        }
    }

    private static void checkForMissingInput(String[] str, String cmd) {
        if (str.length == 1
                && (cmd.equals("mark")
                || cmd.equals("unmark")
                || cmd.equals("delete")
                || cmd.equals("todo")
                || cmd.equals("deadline")
                || cmd.equals("event"))) {
            throw new MissingDukeInputException(cmd);
        }
    }
}
