package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;

public class Ui {
    private static final String DIVIDER = "-------------------------------------\n";

    /**
     * Returns greeting message
     */
    public static String greet() {

        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        return message;
    }

    /**
     * Returns exit message
     */
    public String exit() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns Task ArrayList in string format
     * @param input
     */
    public String listPrint(ArrayList<Task> input) {
        if (input.isEmpty()) {
            return "List is empty\n";
        } else {
            String returnVal = "Here are the list items:\n";
            for (int i = 0; i < input.size(); i++) {
                returnVal += (i + 1) + ". " + input.get(i) + "\n";
            }
            return returnVal;
        }
    }

    /**
     * Returns new Task added, and Task ArrayList size
     * @param type Type of Task
     * @param currTask Task object
     * @param size New size of TaskList
     */
    public String addTask(String type, Task currTask, int size) {
        return "OK, I've added this " + type + ":\n" + "   " +
                currTask + "\n" + "Number of tasks in list: " +
                size + "\n";
    }

    /**
     * Returns the deleted Task, and Task ArrayList size
     * @param currTask Task object
     * @param size New size of TaskList
     */
    public String deleteTask(Task currTask, int size) {
        return "OK, I've removed this task:\n" + "   " +
                currTask + "\n" + "Number of tasks in list: " +
                size + "\n";
    }

    /**
     * Returns the toggled Task
     * @param currTask Task Object
     */
    public String toggleTask(Task currTask) {
        if (currTask.isCompleted()) {
            return "Nice! I've marked this task as done:\n"
                    + "   " + currTask + "\n";
        } else {
            return "OK, I've marked this task as not done yet:\n"
                    + "   " + currTask + "\n";
        }
    }

    /**
     * Returns any exception message handled by Parser
     * @param e Exception
     */
    public String printException(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns tasks matching regex in pretty UI
     * @param input Tasks ArrayList
     * @param regex
     */
    public String find(ArrayList<Task> input, String regex) {
        if (input.isEmpty()) {
            return "List is empty\n";
        } else {
            boolean hasValues = false;
            String returnVal = "Here are the list items matching " + regex + "\n";

            for (int i = 0; i < input.size(); i++) {
                String currLine = input.get(i).toString();
                if (currLine.contains(regex)) {
                    hasValues = true;
                    returnVal += (i + 1) + ". " + currLine + "\n";
                }
            }
            if (!hasValues) {
                return "List has no elements containing " + regex;
            }
            return returnVal;
        }
    }
}
