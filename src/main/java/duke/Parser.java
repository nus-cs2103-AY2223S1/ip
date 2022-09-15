package duke;

/**
 * Parser class helps break down user command and handle any exceptions in the process.
 */
public class Parser {

    /**
     * Parses the command into a simpler form.
     * @param command A String given by the user.
     * @return An array of strings to be handled by Duke.
     * @throws DukeException
     */
    public static String[] parse(String command) throws DukeException {
        String[] splitCommands = command.split(" ", 2);
        switch (splitCommands[0]) {
        case "list":
        case "sort":
        case "bye":
            if (splitCommands.length != 1) {
                throw new DukeException("Do you mean \"%s\"?", splitCommands[0]);
            }
            return splitCommands;

        case "todo":
        case "format":
        case "find":
        case "schedule":
            if (splitCommands.length != 2) {
                throw new DukeException("Missing argument.");
            }
            return splitCommands;

        case "deadline":
        case "event":
            if (splitCommands.length != 2) {
                throw new DukeException("Description cannot be empty.");
            }
            String regex = splitCommands[0].equals("deadline") ? " /by " : " /at ";
            String[] details = splitCommands[1].split(regex);
            if (details.length != 2 || details[0].isEmpty() || details[1].isEmpty()) {
                throw new DukeException("Make sure task description and dates are inputted properly!");
            }
            return new String[]{ splitCommands[0], details[0], details[1] };

        case "mark":
        case "unmark":
        case "delete":
            try {
                Integer.parseInt(splitCommands[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Index must not be blank.");
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid index %s. Index must be an integer.", splitCommands[1]);
            }
            return splitCommands;

        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
