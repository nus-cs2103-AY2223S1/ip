package duke;

import java.util.Scanner;

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
        try {
            if (input.equals("bye")) {
                return duke.handleExit();
            }
            if (input.equals("list")) {
                return duke.handleList();
            }
            if (input.startsWith("mark")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleMark(index);
            }
            if (input.startsWith("unmark")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleUnmark(index);
            }
            if (input.startsWith("todo")) {
                try {
                    String desc = input.substring(5);
                    return duke.handleToDo(desc);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }
            }
            if (input.startsWith("deadline")) {
                int end = input.indexOf('/');
                String desc = input.substring(9, end - 1);
                String by = input.substring(end + 4);
                return duke.handleDeadline(desc, by);
            }
            if (input.startsWith("event")) {
                int end = input.indexOf('/');
                String desc = input.substring(6, end - 1);
                String at = input.substring(end + 4);
                return duke.handleEvent(desc, at);
            }
            if (input.startsWith("delete")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                return duke.handleDelete(index);
            }
            if (input.startsWith("find")) {
                String[] parts = input.split(" ");
                String keyword = parts[1];
                return duke.find(keyword);
            }
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
