package duke;

import java.util.ArrayList;

/**
 * Represents an Ui that handles user interaction.
 */
public class Ui {

    /**
     * Displays goodbye message.
     *
     * @return String representing the goodbye message.
     */
    public String goodBye() {
        return String.format("\tKeep moving forward until you finish all your tasks. Goodbye.\n");
    }

    /**
     * Displays file loading error message.
     */
    public void showLoadingError() {
        System.out.println("I cannot load your file!");
    }

    //Solution adapted from https://github.com/24Donovan24/ip/blob/master/src/main/java/duke/Ui.java

    /**
     * Displays the current list of tasks.
     *
     * @param tasks List of task.
     * @return String representation of task list.
     */
    public String printList(TaskList tasks) {
        StringBuilder builder = new StringBuilder();
        builder.append("\tHere are the task(s) in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
            Task task = tasks.getTask(i);
            builder.append("\n\t");
            builder.append((i + 1) + ". " + task.toString());
        }
        return builder.toString();
    }

    /**
     * Displays the addition of todo task.
     *
     * @param todo todo task specified by the user.
     * @param size Current number of tasks in the list.
     * @return String indicating the addition of todo task.
     */
    public String printToDo(Task todo, int size) {
        return String.format("\tGot it. I've added this task:\n\t%s\n\tNow you have %d task(s) in your list.", todo, size);
    }

    /**
     * Displays the addition of deadline task.
     *
     * @param deadline deadline task specified by the user.
     * @param size Current number of tasks in the list.
     * @return String indicating the addition of deadline task.
     */
    public String printDeadLine(Task deadline, int size) {
        return String.format("\tGot it. I've added this task:\n\t%s\n\tNow you have %d task(s) in your list.", deadline, size);
    }

    /**
     * Displays the addition of event task.
     *
     * @param event event task specified by the user.
     * @param size Current number of tasks in the list.
     * @return String indicating the addition of event task.
     */
    public String printEvent(Task event, int size) {
        return String.format("\tGot it. I've added this task:\n\t%s\n\tNow you have %d task(s) in your list.", event, size);
    }

    /**
     * Displays message when a task has been marked as done.
     *
     * @param task Task to be marked as done.
     * @return String indicating the marking of the task.
     */
    public String printMark(Task task) {
        return String.format("\tNice! You've completed this task:\n\t%s", task);

    }

    /**
     * Displays message when a task has been unmarked.
     *
     * @param task Task to be unmarked.
     * @return String indicating the unmarking of the task.
     */
    public String printUnMark(Task task) {
        return String.format("\tOh no! Try to complete this task ASAP:\n\t%s", task);
    }

    /**
     * Displays message when a task has been deleted.
     *
     * @param task Task to be deleted.
     * @param size Number of tasks left in the list after deletion.
     * @return String indicating the deletion of task.
     */
    public String printDelete(Task task, int size) {
        return String.format("\tTask eliminated:\n\t%s\n\tNow you have %d tasks in your list.", task, size);
    }

    /**
     * Displays list of tasks based on keyword entered by user.
     *
     * @param list Arraylist of filtered task(s).
     * @return String representation of filtered task list.
     */
    public String printFind(ArrayList<Task> list) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the task(s) you are looking for:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            builder.append((i + 1) + ". " + task.toString());
        }
        return builder.toString();
    }

    /**
     * Displays error message.
     *
     * @param msg String representing message to be displayed.
     * @return Error message.
     */
    public String printErrorMessage(String msg) {
        return msg;
    }

    /**
     * Displays all usable commands available in Duke.
     *
     * @return String representation of all commands and their description.
     */
    public String printHelp() {
        StringBuilder builder = new StringBuilder();
        builder.append("These are the commands available for use:");
        builder.append("\n\t");
        builder.append("todo : Add a ToDo type task to the list.");
        builder.append("\n\t");
        builder.append("deadline : Add a Deadline type task to the list.");
        builder.append("\n\t");
        builder.append("event : Add a Event type task to the list.");
        builder.append("\n\t");
        builder.append("list : Display the current list of tasks.");
        builder.append("\n\t");
        builder.append("mark : Mark the task as done.");
        builder.append("\n\t");
        builder.append("unmark : Mark the task as not done yet.");
        builder.append("\n\t");
        builder.append("delete : Remove a task from the list.");
        builder.append("\n\t");
        builder.append("find : Find task(s) with the specified keyword.");
        builder.append("\n\t");
        builder.append("bye : Exit the program.");
        return builder.toString();
    }
}
