package duke.parser;

import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.tasklist.TaskList;

/**
 * Parser to handle user input.
 */
public class Parser {

    /** Scanner to take in user input. */
    private Scanner in;

    /**
     * Constructor for parser object.
     * @param in Scanner object that reads user input.
     */
    public Parser(Scanner in) {
        this.in = in;
    }

    /**
     * Handles user input and calls methods accordingly.
     * @return Boolean to indicate end command has been sent.
     * @throws DukeException In the event that the command is not recognised.
     */
    public boolean handleInput() throws DukeException {
        TaskList tasklist = TaskList.getInstance();

        while (in.hasNext()) {
            String[] temp = in.nextLine().split(" ", 2);
            String[] next = new String[2];
            for (int i = 0; i < temp.length; i++) {
                next[i] = temp[i].trim();
            }

            next[0] = next[0].toLowerCase();
            String command = next[0];

            switch (command) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return true;
            case "list":
                tasklist.list();
                break;
            case "unmark":
                tasklist.unmark(next[1]);
                break;
            case "mark":
                tasklist.mark(next[1]);
                break;
            case "todo":
            case "deadline":
            case "event":
                tasklist.addTask(next);
                break;
            case "find":
                tasklist.findWithFilter(next[1]);
                break;
            case "delete":
                tasklist.delete(next[1]);
                break;
            default:
                throw new DukeException("Invalid command");
            }
        }
        return false;
    }
}

