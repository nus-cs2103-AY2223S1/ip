package duke;

/**
 * Enum class that contains all the possible type of user input.
 */
public enum Command {

    //todo must have some other things behind
    TODO("todo"),
    //deadline need to have deadline, action, /by, date and time
    DEADLINE("deadline"),
    //event need event, action, /at, date and time
    EVENT("event"),
    //bye need only bye nothing else
    BYE("bye"),
    //list need only list nothing else
    LIST("list"),
    //find can only find 1 word, so find and a word
    FIND("find"),
    //mark can only mark one index, so mark and integer
    MARK("mark"),
    //unmark can only unmark one index, so unmark and integer
    UNMARK("unmark"),
    //delete can only delete one index, so delete and integer
    DELETE("delete"),
    UNKNOWN("unknown");

    private final String command;

    Command(String command) {
        this.command = command;
    }


    /**
     * Reads the user input and matches with the command.
     * @param input The User input.
     * @return The Command that matches the user input.
     */
    public static Command read(String input) {
        System.out.println(input);
        String[] strArr = input.split(" ");
        if (strArr.length == 1) {
            System.out.println("or here");
            return singleWord(input);
            }
        if (strArr.length == 2) {
            System.out.println("2 words");
            return twoWords(strArr);
        }

        if (strArr.length > 2) {
            return moreThanTwoWords(input);
        }
        System.out.println("unknown");
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
        System.out.println(input[0] + input[1]);
        switch (input[0]) {
            case "todo":
                return TODO;
            case "mark":
                try {
                    int i = Integer.parseInt(input[1]);
                    return MARK;
                } catch (NumberFormatException e) {
                    return UNKNOWN;
                }
            case "unmark":
                try {
                    int i = Integer.parseInt(input[1]);
                    return UNMARK;
                } catch (NumberFormatException e) {
                    return UNKNOWN;
                }
            case "find":
                return FIND;
            case "delete":
                try {
                    int i = Integer.parseInt(input[1]);
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
        if (strArr[0].equals("deadline")) {
            if (input.contains("/by ")) {
                return DEADLINE;
            } else {
                return UNKNOWN;
            }
        } else if (strArr[0].equals("event")) {
            if (input.contains("/at ")) {
                return EVENT;
            } else {
                return UNKNOWN;
            }
        } else if (strArr[0].equals("todo")) {
            return TODO;
        } else {
            return UNKNOWN;
        }
    }

}
