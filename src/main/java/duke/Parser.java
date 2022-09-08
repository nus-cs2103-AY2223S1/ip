package duke;


import java.util.Locale;

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
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleMark(index);
            }
            if (trimmedInput.toLowerCase().startsWith("unmark")) {
                String[] parts = trimmedInput.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleUnmark(index);
            }
            if (trimmedInput.toLowerCase().startsWith("todo")) {
                try {
                    String desc = trimmedInput.substring(5);
                    return duke.handleToDo(desc);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            if (trimmedInput.toLowerCase().startsWith("deadline")) {
                int end = trimmedInput.indexOf('/');
                String desc = trimmedInput.substring(9, end - 1);
                String by = trimmedInput.substring(end + 4);
                return duke.handleDeadline(desc, by);
            }
            if (trimmedInput.toLowerCase().startsWith("event")) {
                int end = trimmedInput.indexOf('/');
                String desc = trimmedInput.substring(6, end - 1);
                String at = trimmedInput.substring(end + 4);
                return duke.handleEvent(desc, at);
            }
            if (trimmedInput.toLowerCase().startsWith("delete")) {
                String[] parts = trimmedInput.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleDelete(index);
            }
            if (trimmedInput.toLowerCase().startsWith("find")) {
                String[] parts = trimmedInput.split(" ");
                String keyword = parts[1];
                return duke.handleFind(keyword);
            }
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
