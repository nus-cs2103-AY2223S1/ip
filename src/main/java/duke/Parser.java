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
    public void parse() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            try {
                switch (input) {
                    case "list":
                        duke.printList();
                        break;
                    case "mark": {
                        int index = sc.nextInt() - 1;
                        duke.handleMark(index);
                        break;
                    }
                    case "unmark": {
                        int index = sc.nextInt() - 1;
                        duke.handleUnmark(index);
                        break;
                    }
                    case "todo": {
                        String next = sc.nextLine();
                        input += next;
                        duke.handleToDo(input);
                        break;
                    }
                    case "deadline": {
                        input += sc.nextLine();
                        duke.handleDeadline(input);
                        break;
                    }
                    case "event": {
                        input += sc.nextLine();
                        duke.handleEvent(input);
                        break;
                    }
                    case "delete": {
                        int index = sc.nextInt() - 1;
                        duke.handleDelete(index);
                        break;
                    }
                    default:
                        input += sc.nextLine();
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                duke.printException(e);
            }
            input = sc.next();
        }
    }
}
