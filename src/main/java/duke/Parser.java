package duke;

/**
 * Parser that takes user input and outputs commands DukeBot can understand.
 */
public class Parser {

    private static boolean isValidCommand(String cmd) {
        if (cmd.equals("mark") || cmd.equals("unmark")
                || cmd.equals("delete") || cmd.equals("todo")
                || cmd.equals("deadline") || cmd.equals("event")
                || cmd.equals("find")) {
            return true;
        } else {
            return false;
        }
    }

    private static String[] formatResults(String cmd) {
        String[] result = new String[3];
        result[0] = cmd;
        return result;
    }

    private static String[] formatResults(String cmd, String description) {
        String[] result = new String[3];
        result[0] = cmd;
        result[1] = description;
        return result;
    }

    private static String[] formatResults(String cmd, String description, String time) {
        String[] result = new String[3];
        result[0] = cmd;
        result[1] = description;
        result[2] = time;
        return result;
    }

    /**
     * Static method used to parse player inputs into a String array
     * that is understood by Duke.
     *
     * @param s String input by user to be parsed.
     * @return String array to be processed by Duke.
     * @throws InvalidTaskException Thrown if user input wrong task format.
     * @throws EmptyDescriptionException Throw if task description is empty.
     * @throws InvalidFormatException Thrown if invalid task format.
     */
    public static String[] parseInput(String s) throws InvalidTaskException,
            EmptyDescriptionException, InvalidFormatException {
        s = s.trim();
        String[] helper = s.split(" ");
        String cmd = helper[0];

        if (helper.length == 1) {
            if (cmd.equals("bye")) {
                return formatResults(cmd);

            } else if (cmd.equals("list")) {
                return formatResults(cmd);

            } else if (isValidCommand(cmd)) {
                throw new EmptyDescriptionException("Empty descriptor", cmd);
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        }

        if (cmd.equals("mark")) {
            if (helper.length == 2) {
                return formatResults(cmd, helper[1]);
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        } else if (cmd.equals("unmark")) {
            if (helper.length == 2) {
                return formatResults(cmd, helper[1]);
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        } else if (cmd.equals("delete")) {
            if (helper.length == 2) {
                return formatResults(cmd, helper[1]);
            } else {
                throw new InvalidTaskException("No valid task descriptor");
            }
        } else if (helper[0].equals("find")) {
            String[] findDescription = s.split(" ", 2);
            return formatResults(cmd, findDescription[1]);

        } else if (helper[0].equals("todo")) {
            String[] todoString = s.split(" ", 2);
            return formatResults(cmd, todoString[1]);

        } else if (helper[0].equals("deadline")) {
            String description = s.substring(8).trim();
            String[] temp = description.split("/by", 2);

            if (!s.contains("/by")) {
                throw new InvalidFormatException("Invalid format");
            }
            if (temp[1].trim().equals("")) {
                throw new InvalidTaskException("Invalid format");

            }
            return formatResults(cmd, temp[0].trim(), temp[1].trim());

        } else if (helper[0].equals("event")) {
            String description = s.substring(6).trim();
            String[] temp = description.split("/at", 2);

            if (!s.contains("/at")) {
                throw new InvalidFormatException("Invalid format");
            }
            if (temp[1].trim().equals("")) {
                throw new InvalidFormatException("Invalid format");
            }
            return formatResults(cmd, temp[0].trim(), temp[1].trim());

        } else {
            throw new InvalidTaskException("No valid task descriptor");
        }
    }

}
