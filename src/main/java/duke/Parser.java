package duke;

import java.util.Arrays;

public class Parser {
    private static void fillNullElementsWithEmptyString(String[] arr) {
        for (String i : arr) {
            if (i == null) {
                i = "";
            }
        }
    }

    public static String[] parseInput(String input)
            throws BannedDukeCharacterException,
            InvalidDukeInputException,
            MissingDukeInputException {
        String[] parsedOutput = new String[6];
        input = input.trim();
        if (input.contains("|")) {
            throw new BannedDukeCharacterException("|");
        }
        switch (input) {
        case "bye":
        case "list":
            parsedOutput[0] = input;
            return parsedOutput;
        default:
            String[] str = input.split(" ", 2);
            String cmd = str[0];
            if (str.length == 1 &&
                    (cmd.equals("mark")
                            || cmd.equals("unmark")
                            || cmd.equals("delete")
                            || cmd.equals("todo")
                            || cmd.equals("deadline")
                            || cmd.equals("event"))) {
                throw new MissingDukeInputException(cmd);
            }
            parsedOutput[0] = cmd;

            switch (cmd) {
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
}
