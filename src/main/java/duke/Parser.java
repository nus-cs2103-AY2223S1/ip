package duke;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The parser for the duke program.
 * Processes user string input.
 */
public class Parser {

    /**
     * Parses a user's input, and calls the necessary functions.
     *
     * @param input The user's input.
     * @param tasks The TaskList being used.
     * @param storage The storage object being used.
     */
    public static String parse(String input, TaskList tasks, Storage storage) {
        ArrayList<String> split = new ArrayList<String>(Arrays.asList(input.split(" ")));
        String first = split.remove(0);
        String rest = String.join(" ", split);

        if (first.equals("list")) {
            //Handle listing of tasks
            return tasks.listTasks();

        } else if (first.equals("mark")) {
            //Mark a task as done
            try {
                String message = tasks.markTask(rest);
                storage.writeToFile(tasks);
                return message;
            } catch (DukeException ex) {
                return ex.toString();
            }
        } else if (first.equals("unmark")) {
            //Mark a task as not done
            try {
                String message = tasks.unmarkTask(rest);
                storage.writeToFile(tasks);
                return message;
            } catch (DukeException ex) {
                return ex.toString();
            }
        } else if (first.equals("delete")) {
            //Delete a task at a given index
            try {
                String message = tasks.deleteTask(rest);
                storage.writeToFile(tasks);
                return message;
            } catch (DukeException ex) {
                return ex.toString();
            }
        } else if (first.equals("todo") || first.equals("deadline") || first.equals("event")) {
            //Else, add task to list
            try {
                String message = tasks.addTask(rest, first, false, false);
                storage.writeToFile(tasks);
                return message;
            } catch (DukeException ex) {
                return ex.toString();
            }
        } else if (first.equals("find")) {
            try {
                return tasks.find(rest);
            } catch (DukeException ex) {
                return ex.toString();
            }
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    /**
     * Checks if the given input is the exit command, "bye".
     *
     * @param input The user's input.
     * @return Whether the input is equals to "bye".
     */
    public static boolean checkExit(String input) {
        return input.equals("bye");
    }
}
