package duke;

import java.util.ArrayList;

import duke.exceptions.DukeException;

public class Ui {
    private static final String DIVIDER = "-------------------------------------\n";

    /**
     * Prints the greeting message
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(DIVIDER + message + DIVIDER);
    }

    /**
     * Prints the exit message
     */
    public void exit() {
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    /**
     * Prints the TaskList's Tasks in pretty UI
     */
    public void listPrint(ArrayList<Task> input) {
        if (input.isEmpty()) {
            System.out.println(DIVIDER + "List is empty\n" + DIVIDER);
        } else {
            System.out.print(DIVIDER);
            for (int i = 0; i < input.size(); i++) {
                System.out.println((i + 1) + ". " + input.get(i));
            }
            System.out.println(DIVIDER);
        }
    }

    /**
     * Prints the newly added task to TaskList
     * @param type Type of Task
     * @param currTask Task object
     * @param size New size of TaskList
     */
    public void addTask(String type, Task currTask, int size) {
        System.out.printf(DIVIDER + "OK, I've added this %s:\n %s\n"
                + "Number of tasks in list: %d\n" + DIVIDER + "\n",
                type, currTask, size);
    }

    /**
     * Prints the newly deleted task from TaskList
     * @param currTask Task object
     * @param size New size of TaskList
     */
    public void deleteTask(Task currTask, int size) {
        System.out.printf(DIVIDER + "OK, I've removed this task:\n"
                + "  %s \nNumber of tasks in list: %d\n" + DIVIDER + "\n",
                currTask, size);
    }

    /**
     * Prints the newly toggled task from TaskList
     * @param currTask Task Object
     */
    public void toggleTask(Task currTask) {
        if (currTask.isCompleted()) {
            System.out.println(DIVIDER + "Nice! I've marked this task as done:\n"
                    + "  " + currTask + "\n" + DIVIDER);
        } else {
            System.out.println(DIVIDER + "OK, I've marked this task as not done yet:\n"
                    + "  " + currTask + "\n" + DIVIDER);
        }
    }

    /**
     * Prints any exception message handled by Parser in pretty UI
     * @param e Exception
     */
    public void printException(DukeException e) {
        System.out.println(DIVIDER + e.getMessage() + DIVIDER);
    }

    /**
     * Prints out tasks matching regex in pretty UI
     * @param input Tasks ArrayList
     * @param regex
     */
    public void find(ArrayList<Task> input, String regex) {
        if (input.isEmpty()) {
            System.out.println(DIVIDER + "List is empty\n" + DIVIDER);
        } else {
            boolean hasValues = false;
            System.out.print(DIVIDER);
            for (int i = 0; i < input.size(); i++) {
                String currLine = input.get(i).toString();
                if (currLine.contains(regex)) {
                    hasValues = true;
                    System.out.println((i + 1) + ". " + currLine);
                }
            }
            if (!hasValues) {
                System.out.println("List has no elements containing " + regex);
            }
            System.out.println(DIVIDER);
        }
    }
}
