package duke;


public enum Command {


    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    LIST("list"),
    FIND("find"),
    MARK("mark"),
    UNMARK("unmark"),
    DELETE("delete"),
    UNKNOWN("unknown");


    Command(String command) {

    }


    /**
     * Reads the user input and matches with the command.
     * @param input The User input.
     * @return The Command that matches the user input.
     */
    public static Command read(String input) {
        String[] strArr = input.split(" ");
        if (strArr.length == 1) {
            return singleWord(input);
            }
        if (strArr.length == 2) {
            return twoWords(strArr);
        }

        if (strArr.length > 2) {
            return moreThanTwoWords(input);
        }
        return UNKNOWN;
    }


    /**
     * Check what Command it can be based on input that is 1 word long.
     * @param input The user input.
     * @return The corresponding Command for the input.
     */
    private static Command singleWord(String input) {
        if (input.equals("bye")) {
            return BYE;
        } else if (input.equals("list")) {
            return LIST;
        } else {
            return UNKNOWN;
        }
    }

    /**
     * Check what Command it can be based on input that is 2 words long.
     * @param input The user input.
     * @return The corresponding Command for the input.
     */
    private static Command twoWords(String[] input) {
        switch (input[0]) {
            case "todo":
                return TODO;
            case "mark":
                try {
                    return MARK;
                } catch (NumberFormatException e) {
                    return UNKNOWN;
                }
            case "unmark":
                try {
                    return UNMARK;
                } catch (NumberFormatException e) {
                    return UNKNOWN;
                }
            case "find":
                return FIND;
            case "delete":
                try {
                    return DELETE;
                } catch (NumberFormatException e) {
                    return UNKNOWN;
                }
            default:
                return UNKNOWN;
        }
    }

    /**
     * Check what Command it can be based on input that are more than 2 words long.
     * @param input The user input.
     * @return The corresponding Command for the input.
     */
    private static Command moreThanTwoWords(String input) {
        String[] strArr = input.split(" ");
        switch (strArr[0]) {
            case "deadline":
                if (input.contains("/by ")) {
                    return DEADLINE;
                } else {
                    return UNKNOWN;
                }
            case "event":
                if (input.contains("/at ")) {
                    return EVENT;
                } else {
                    return UNKNOWN;
                }
            case "todo":
                return TODO;
            default:
                return UNKNOWN;
        }
    }

}
