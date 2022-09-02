package duke;

import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * A parser which handles parsing of the user input.
 */
public class Parser {
    private boolean hasExited;

    /**
     * Constructor method for a Parser.
     */
    public Parser() {
        this.hasExited = false;
    }

    /**
     * Tests whether the user has exited.
     *
     * @return true if the user has exited, false otherwise
     */
    public boolean isExit() {
        return this.hasExited;
    }

    /**
     * Parses the user input.
     *
     * @param input input from the user
     * @param tasks list of tasks of the user
     * @throws DukeException if there is an error with the input
     */
    public void parse(String input, TaskList tasks) throws DukeException {
        if (input.equals("bye")) {
            this.hasExited = true;
        } else if (input.equals("list")) {
            tasks.showList();
        } else if (input.startsWith("mark")) {
            if (input.length() < 6) {
                throw new DukeException("Oops, no task given to mark as done.");
            }
            int i = Integer.parseInt(input.substring(5)) - 1;
            tasks.markTask(i, true);
        } else if (input.startsWith("unmark")) {
            if (input.length() < 8) {
                throw new DukeException("Oops, no task given to mark as not done.");
            }
            int i = Integer.parseInt(input.substring(7)) - 1;
            tasks.markTask(i, false);
        } else if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException("Oops, the description of a todo task cannot be empty.");
            }
            String desc = input.substring(5);
            Task t = new Todo(desc);
            tasks.addTask(t);
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                throw new DukeException("Oops, the description of a deadline task cannot be empty.");
            } else if (!input.contains("/by")) {
                throw new DukeException("Oops, no deadline given for deadline task.");
            }
            String[] str = input.split(" /by ", 2);
            String s1 = str[0].substring(9);
            Task t = new Deadline(s1, str[1]);
            tasks.addTask(t);
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                throw new DukeException("Oops, the description of an event task cannot be empty.");
            } else if (!input.contains("/at")) {
                throw new DukeException("Oops, no date given for event task.");
            }
            String[] str = input.split(" /at ", 2);
            String s1 = str[0].substring(6);
            Task t = new Event(s1, str[1]);
            tasks.addTask(t);
        } else if (input.startsWith("delete")) {
            if (input.length() < 8) {
                throw new DukeException("Oops, no task given to delete.");
            }
            int i = Integer.parseInt(input.substring(7)) - 1;
            tasks.deleteTask(i);
        } else if (input.startsWith("date")) {
            if (input.length() < 6) {
                throw new DukeException("Oops, no date given.");
            }
            String dateStr = input.substring(5);
            tasks.getDateTasks(dateStr);
        } else {
            throw new DukeException("Oops, I don't know what that means");
        }
    }
}
