package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.exceptions.DukeException;

/** Represents a Ui object. Handles response strings. */
public class Ui {
    private static final String DIVIDER = "-------------------------------------\n";

    /** Returns greeting message */
    public static String greet() {

        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";

        return message;
    }

    /** Returns exit message */
    public String exit() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns Task ArrayList in string format
     * @param input
     * @return String
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
     * Returns string representation of new Task added, and Task ArrayList size
     * @param type Type of Task
     * @param currTask Task object
     * @param size New size of TaskList
     * @return String
     */
    public String addTask(String type, Task currTask, int size) {
        return "OK, I've added this " + type + ":\n" + "   "
                + currTask + "\n" + "Number of tasks in list: "
                + size + "\n";
    }

    /**
     * Returns string representation of deleted Task, and Task ArrayList size
     * @param currTask Task object
     * @param size New size of TaskList
     * @return String
     */
    public String deleteTask(Task currTask, int size) {
        return "OK, I've removed this task:\n" + "   "
                + currTask + "\n" + "Number of tasks in list: "
                + size + "\n";
    }

    /**
     * Returns string representation the toggled Task
     * @param currTask Task Object
     * @return String
     */
    public String toggleTask(Task currTask) {
        if (currTask.getIsCompleted()) {
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
     * @return String
     */
    public String printException(DukeException e) {
        return e.getMessage();
    }

    /**
     * Returns tasks matching regex in pretty UI
     * @param input Tasks ArrayList
     * @param regex
     * @return String
     */
    public String find(ArrayList<Task> input, String regex) {
        if (input.isEmpty()) {
            return "List is empty\n";
        } else {
            boolean hasValues = false;
            String returnVal = "Here are the list items matching " + regex + ":\n";

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

    /**
     * Returns Deadlines due/ Events occurring within 2 weeks of current day
     * @param input
     * @return String
     */
    public String remind(ArrayList<Task> input) {
        LocalDate now = LocalDate.now().minusDays(1);
        LocalDate end = now.plusWeeks(2).plusDays(2);

        if (input.isEmpty()) {
            return "List is empty\n";
        } else {
            boolean hasValues = false;
            String returnVal = "Here are tasks due in the next two weeks:\n";

            for (int i = 0; i < input.size(); i++) {
                Task currTask = input.get(i);
                if (!currTask.isDated()) {
                    continue;
                }
                Dated datedTask = (Dated) currTask;
                if (datedTask.isBetween(now, end)) {
                    hasValues = true;
                    returnVal += (i + 1) + ". " + currTask.toString() + "\n";
                }
            }
            if (!hasValues) {
                return "There are no tasks due in the next two weeks.\n";
            }
            return returnVal;
        }
    }
}
