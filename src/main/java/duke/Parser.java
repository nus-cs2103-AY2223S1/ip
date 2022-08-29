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
     * @param ui The Ui instance that has been initialised.
     */
    public static void parse(String input, TaskList tasks, Storage storage, Ui ui) {
        ArrayList<String> split = new ArrayList<String>(Arrays.asList(input.split(" ")));
        String first = split.remove(0);
        String rest = String.join(" ", split);

        if (first.equals("list")) {
            //Handle listing of tasks
            ui.say(tasks.listTasks());

        } else if (first.equals("mark")) {
            //Mark a task as done
            try {
                ui.say(tasks.markTask(rest));
                storage.writeToFile(tasks);
            } catch (DukeException ex) {
                ui.say(ex.toString());
            }
        } else if (first.equals("unmark")) {
            //Mark a task as not done
            try {
                ui.say(tasks.unmarkTask(rest));
                storage.writeToFile(tasks);
            } catch (DukeException ex) {
                ui.say(ex.toString());
            }
        } else if (first.equals("delete")) {
            //Delete a task at a given index
            try {
                ui.say(tasks.deleteTask(rest));
                storage.writeToFile(tasks);
            } catch (DukeException ex) {
                ui.say(ex.toString());
            }
        } else if (first.equals("todo") || first.equals("deadline") || first.equals("event")) {
            //Else, add task to list
            try {
                ui.say(tasks.addTask(rest, first, false, false));
                storage.writeToFile(tasks);
            } catch (DukeException ex) {
                ui.say(ex.toString());
            }
        } else if (first.equals("find")) {
            try {
                ui.say(tasks.find(rest));
            } catch (DukeException ex) {
                ui.say(ex.toString());
            }
        } else {
            ui.say("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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
