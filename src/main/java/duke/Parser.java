package duke;

public class Parser {
    public static String[] parse(String command) throws DukeException {
        String[] splitCommands = command.split(" ", 2);
        switch (splitCommands[0]) {
        case "list":
        case "sort":
        case "bye":
            return splitCommands;

        case "todo":
            if (splitCommands.length == 1) {
                throw new DukeException("\u2639 OOPS!!! Description cannot be empty.");
            }
            return splitCommands;

        case "format":
            if (splitCommands.length == 1) {
                throw new DukeException("\u2639 OOPS!!! Format cannot be blank.");
            }
            return splitCommands;
        
        case "deadline":
        case "event":
            if (splitCommands.length == 1) {
                throw new DukeException("\u2639 OOPS!!! Description cannot be empty.");
            }
            String regex = splitCommands[0].equals("deadline") ? " /by " : " /at ";
            String[] details = splitCommands[1].split(regex);
            if (details.length != 2 || details[0].isEmpty() || details[1].isEmpty()) {
                throw new DukeException("\u2639 OOPS!!! Please make sure task description and dates are inputted properly!");
            }
            return new String[]{ splitCommands[0], details[0], details[1] };
    
        case "mark":
        case "unmark":
        case "delete":
            try {
                Integer.parseInt(splitCommands[1]);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! Index must not be blank.");
            } catch (NumberFormatException e) {
                throw new DukeException("\u2639 OOPS!!! Invalid index %s. Index must be an integer.", splitCommands[1]);
            }
            return splitCommands;

        default:
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
