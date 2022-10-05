package duke;

/**
 * Parser class that parses input string and dispatches relevant responses.
 */
public class Parser {
    private Duke duke;

    /**
     * Constructor for Parser Class.
     *
     * @param duke The duke object being used.
     */
    public Parser(Duke duke) {
        this.duke = duke;
    }

    /**
     * Parses input string from user and dispatches an action accordingly.
     * Prints exception to the console if input is of invalid form.
     */
    public String parse(String input) {
        String trimmedInput = input.trim();
        try {
            if (trimmedInput.equalsIgnoreCase("bye")) {
                return duke.handleExit();
            }
            if (trimmedInput.equalsIgnoreCase("list")) {
                return duke.handleList();
            }
            if (trimmedInput.toLowerCase().startsWith("mark")) {
                String[] parts = trimmedInput.split(" ");
                validateCommand(parts, 2);
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleMark(index);
            }
            if (trimmedInput.toLowerCase().startsWith("unmark")) {
                String[] parts = trimmedInput.split(" ");
                validateCommand(parts, 2);
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleUnmark(index);
            }
            if (trimmedInput.toLowerCase().startsWith("todo")) {
                String[] parts = trimmedInput.split(" ", 2);
                validateCommand(parts, 2);
                String desc = parts[1];
                return duke.handleToDo(desc);
            }
            if (trimmedInput.toLowerCase().startsWith("deadline")) {
                String[] parts = trimmedInput.split(" ", 2);
                validateCommand(parts, 2);
                String[] details = parts[1].split("/by", 2);
                validateCommand(details, 2);
                String desc = details[0].trim();
                String by = details[1].trim();
                return duke.handleDeadline(desc, by);
            }
            if (trimmedInput.toLowerCase().startsWith("event")) {
                String[] parts = trimmedInput.split(" ");
                validateCommand(parts, 4);
                String desc = parts[1];
                String at = parts[3];
                return duke.handleEvent(desc, at);
            }
            if (trimmedInput.toLowerCase().startsWith("delete")) {
                String[] parts = trimmedInput.split(" ");
                validateCommand(parts, 2);
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleDelete(index);
            }
            if (trimmedInput.toLowerCase().startsWith("find")) {
                String[] parts = trimmedInput.split(" ");
                validateCommand(parts, 2);
                String keyword = parts[1];
                return duke.handleFind(keyword);
            }
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void validateCommand(String[] input, int expectedLength) throws DukeException {
        if (input.length == 1 || input[0].trim().isEmpty() || input[1].trim().isEmpty()) {
            throw new DukeException("OOPS!!! Your command is incomplete!");
        }
        if (input.length < expectedLength) {
            throw new DukeException("OOPS!!! Your command is of the wrong format!");
        }
    }
}
