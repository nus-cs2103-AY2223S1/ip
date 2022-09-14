package duke.tasks;

import duke.functions.Ui;

/**
 * Task class for tasks that users input into the Duke bot.
 *
 * @author lauralee
 */
public class Task {

    private String name;
    private String type;

    // Status of a task. The status of the task will be 0 when the task is unamrked and 1 when it is marked.
    private int status;
    private static int NUMBER_TASKS = 0;

    /**
     * Constructor for the Task class.
     *
     * @param name Description of the Task being added.
     * @param type Type of the Task being added, i.e. T, E or D.
     */
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.status = 0;
        NUMBER_TASKS++;
    }

    /**
     * Overloaded constructor for Task class.
     *
     * @param name Description of the Task being added.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks a Task.
     */
    public String mark() {
        this.status = 1;
        return Ui.printMark(this.type, this.name);
    }

    /**
     * Unmarks a Task.
     */
    public String unMark() {
        this.status = 0;
        return Ui.printUnmark(this.type, this.name);
    }

    /**
     * Indicates the numbers of Tasks that has been added by the user.
     *
     * @return The number of Tasks that has been added by the user.
     */
    public static int getNumberTasks() {
        return NUMBER_TASKS;
    }

    /**
     * Decrements the number of tasks by 1 when a task is deleted from the list.
     */
    public static void deleteTask(){
        NUMBER_TASKS--;
    }

    /**
     * The description of the Task added including its type, name and when it needs to be completed by.
     *
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

