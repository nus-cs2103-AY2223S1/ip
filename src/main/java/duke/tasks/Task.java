package duke.tasks;

import duke.functions.Ui;

/**
 * Task class for tasks that users input into the Duke bot.
 * @author lauralee
 */
public class Task {
    private String name;
    private String type;
    private int status;
    public static int numberTasks = 0;
    private String description;

    /**
     * Constructor for the Task class.
     * @param name Name of the Task being added.
     * @param type Type of the Task being added, i.e. T, E or D.
     */
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.status = 0;
        numberTasks++;
    }

    /**
     * Overloaded constructor for Task class.
     * @param description Description of the Task being added.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks a Task.
     */
    public void mark() {
        this.status = 1;
        Ui.printMark(this.type, this.name);
    }

    /**
     * Unmarks a Task.
     */
    public void unMark() {
        this.status = 0;
        Ui.printUnmark(this.type, this.name);
    }

    /**
     * Indicates the numbers of Tasks that has been added by the user.
     * @return The number of Tasks that has been added by the user.
     */
    public static int getNumberTasks() {
        return numberTasks;
    }

    /**
     * The status of the task, whether it has been marked or not.
     * @return A 0 if the Task is unmarked, and 1 if the Task has been marked.
     */
    public int getStatus() {
        return status;
    }

    /**
     * The description of the Task added including its type, name and when it needs to be completed by.
     * @return The description of a Task.
     */
    public String output() {
        if (this.status == 0) {
            return "[" + this.type + "][ ] " + this.name;
        } else {
            return "[" + this.type + "][X] " + this.name;
        }
    }

}

