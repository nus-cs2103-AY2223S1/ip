package chatbot.ui;

import chatbot.exceptions.DukeException;
import chatbot.tasks.Task;

import java.util.List;

public class Response {
    private static final String EMOJI = "<_>";
    public static final String SLEEPING = "You said bye already, I am resting now. Restart, if you wish wish to talk again.";

    public String greet() {
        return "Yes? I'm Zlimez~~ \nWhat can I possibly do for you?\n >>>^<<<";
    }

    /**
     * Displays all the tasks in the todo list.
     * @param tasks The list containing all the tasks.
     */
    public String listAll(List<Task> tasks) {
        return String.format("Really? If you are so forgetful...\n%s", list(tasks));
    }

    /**
     * Displays the tasks in the todo list on the specified date.
     * @param tasks The list containing the relevant tasks.
     */
    public String listTaskOn(List<Task> tasks) {
        if (tasks != null) {
            return String.format("These are your tasks for that day\n%s", list(tasks));
        } else {
            return "Well you are a lazy bum, you have nothing on the day";
        }
    }

    /**
     * Displays the tasks that contain a given keyword.
     * @param tasks The list containing the relevant tasks.
     */
    public String listFound(List<Task> tasks) {
        if (!tasks.isEmpty()) {
            return "These are the tasks that match your description\n" + list(tasks);
        } else {
            return "You like to search for nothing and waste time huh?";
        }
    }

    private StringBuffer list(List<Task> tasks) {
        StringBuffer todos = new StringBuffer();
        for (int i = 1; i <= tasks.size(); i++) {
            todos.append("\t" + i + ". " + tasks.get(i - 1) + "\n");
        }

        return todos;
    }

    /**
     * Informs user that the given task has been added and the new todo list status.
     *
     * @param target The task added.
     * @param numberOfTasks The number of tasks after the addition.
     */
    public String add(Task target, int numberOfTasks) {
        return String.format("Lazily added this task for you %s\n\t%s\nWala now you have %d tasks in the list.",
                EMOJI, target, numberOfTasks);
    }

    /**
     * Informs user that the given task has been removed and the new todo list status.
     *
     * @param target The task removed.
     * @param numberOfTasks The number of tasks after the deletion.
     */
    public String delete(Task target, int numberOfTasks) {
        return String.format("YES, I've removed this task for YOU:\n\t%s\nWala now you have %d tasks in the list.",
                target, numberOfTasks);
    }

    /**
     * Informs user that the given task has been marked.
     *
     * @param target The task marked as complete.
     */
    public String unmark(Task target) {
        return String.format("-_-, I've unmarked this task for YOU AGAIN:\n\t%s", target);
    }

    /**
     * Informs user that the given task has been unmarked.
     *
     * @param target The task marked as incomplete.
     */
    public String mark(Task target) {
        return String.format("Wellz, I've marked this task for YOU:\n\t%s", target);
    }

    /**
     * Displays the exception that has been raised.
     *
     * @param e The exceptions raised.
     */
    public String reprimand(DukeException e) {
        return e.getMessage();
    }

    /**
     * Says goodbye to the user.
     */
    public String bye() {
        return "Bye. zzz FINALLY~~" + " " + EMOJI;
    }
}
