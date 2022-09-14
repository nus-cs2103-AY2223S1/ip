package duke;
import java.util.ArrayList;

/**
 * Ui class that deals with the interaction with the users.
 */
public class Ui {
    /**
     * Displays a goodbye message.
     */
    public String bye() {
        return String.format("ByeBye! Can't wait to see you again!\n");
    }

    /**
     * Displays a file loading error message.
     */
    public String showErrorMessage(String message) {
        return message;
    }

    /**
     * Displays the todo task added with its corresponding index.
     *
     * @param todo The todo task specified by user.
     * @param size The number of tasks in the list.
     * @return A String to indicate successful addition of todo.
     */
    public String printTodo(Task todo, int size) {
        return String.format("Okay! I've added this task:\n%s\nNow you have %d tasks in the list.", todo, size);
    }

    /**
     * Displays the deadline task added with its corresponding index.
     *
     * @param deadline The deadline task specified by user.
     * @param size The number of tasks in the list.
     * @return A String to indicate successful addition of deadline.
     */
    public String printDeadline(Task deadline, int size) {
        return String.format("Okay! I've added this task:\n%s\nNow you have %d tasks in the list.",
                deadline, size);
    }

    /**
     * Displays the event task added with its corresponding index.
     *
     * @param event The event task specified by user.
     * @param size The number of tasks in the list.
     * @return A String to indicate successful addition of event.
     */
    public String printEvent(Task event, int size) {
        return String.format("Okay! I've added this task:\n%s\nNow you have %d tasks in the list.",
                event, size);
    }

    /**
     * Displays the list of tasks the user has inputted thus far.
     *
     * @param tasks The list of tasks user has inputted.
     * @return A String to indicate the lists of tasks by users.
     */
    public String listTasks(TaskList tasks) {
        int markedTasks = 0;
        int unmarkedTasks = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list so far!");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            sb.append("\n");
            sb.append(i + 1 + ". " + task.toString());
            if (task.isDone) {
                markedTasks++;
            }
        }
        unmarkedTasks = tasks.getSize() - markedTasks;
        if (unmarkedTasks == 0) {
            sb.append("\nWow! You have completed all your tasks! Congratulations!");
        } else {
            sb.append("\nYou have completed " + markedTasks + " task(s) so far.\n"
                    + unmarkedTasks + " more tasks to go! Keep it up!");
        }
        return sb.toString();
    }

    /**
     * Displays the message that the specified task has been deleted.
     *
     * @param task The task to be deleted.
     * @param size The number of tasks left after deletion.
     * @return A String to indicate successful deletion of task.
     */
    public String printDelete(Task task, int size) {
        return String.format("Okay!. I've removed this task:\n%s" + "\nNow you have %d tasks in the list.",
                task, size);
    }

    /**
     * Displays the message that the specified task has been marked.
     *
     * @param task The task to be marked as done.
     * @return A String to indicate successful marking of task.
     */
    public String printMarkedTask(Task task) {
        return String.format("Yay! I've marked this task as done:\n%s", task);
    }

    /**
     * Displays the message that the specified task has been unmarked.
     *
     * @param task The task to be marked as undone.
     * @return A String to indicate successful unmarking of task.
     */
    public String printUnmarkedTask(Task task) {
        return String.format("Okay.. I've marked this task as not done yet:\n%s", task);
    }

    /**
     * Displays the list of tasks based on the keyword user specify.
     *
     * @param lst An ArrayList of the tasks filtered by keyword.
     * @return A String to indicate the lists of tasks filtered by user's keyword.
     */
    public String printFind(ArrayList<Task> lst) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks with your keyword!");
        for (int i = 0; i < lst.size(); i++) {
            Task task = lst.get(i);
            sb.append("\n");
            sb.append(i + 1 + ". " + task.toString());
        }
        return sb.toString();
    }


}
