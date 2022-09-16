package duke;

import duke.task.Note;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Deals with interactions with the user.
 *
 * @author Lim Ai Lin
 */
public class Ui {

    /**
     * Prints a message when Duke faces a loading error.
     */
    public void showLoadingError() {
        System.out.println("RAWR! Error loading previous tasks.");
    }

    /**
     * Returns the duke exception.
     * @param str The error message.
     * @return The String of the error message.
     */
    public static String showError(String str) {
        return str;
    }

    /**
     * Returns a String when the user wishes to exit.
     */
    public String exit() {
        return "RAWR!! Byeeee. Hope to see you again soon!";
    }

    /**
     * Returns a String when the user has marked a task.
     *
     * @param myTask The task that the user has marked.
     */
    public String complete(Task myTask) {
        return "RAWR! You have completed this task:\n" + myTask;
    }

    /**
     * Returns a String when the user has unmarked a task.
     *
     * @param myTask The task that the user has unmarked.
     */
    public String incomplete(Task myTask) {
        return "RAWR! You have more things to complete:\n" + myTask;
    }

    /**
     * Returns a String when the user has added a new task to the list.
     *
     * @param taskList The tasklist the user has added to.
     * @param task The task the user has added.
     */
    public String add(TaskList taskList, Task task) {
        return "Dino added: " + task
        + "\nYou have " + taskList.getTaskSize() + " task" + (taskList.getTaskSize() > 1 ? "s!" : "!");
    }

    /**
     * Returns a String containing all tasks with the matching keyword.
     *
     * @param matching The tasklist containing all tasks with the matching keyword.
     */
    public String matchTask(ArrayList<Task> matching) {
        StringBuilder builder = new StringBuilder();
        if (matching.size() > 0) {
            builder.append("RAWR! Here are the matching tasks Dino found:\n");

            for (int i = 0; i < matching.size(); i++) {
                builder.append(i + 1).append(". ").append(matching.get(i).toString()).append("\n");
            }
        } else {
            builder.append("RAWR! There are no matching tasks!\n");
        }

        return builder.toString();
    }

    /**
     * Returns a String containing all notes with the matching keyword.
     *
     * @param matching The notelist containing all notes with the matching keyword.
     */
    public String matchNote(ArrayList<Note> matching) {
        StringBuilder builder = new StringBuilder();
        if (matching.size() > 0) {
            builder.append("RAWR! Here are the matching notes Dino found:\n");

            for (Note note : matching) {
                builder.append("> ").append(note.toString()).append("\n");
            }
        } else {
            builder.append("RAWR! There are no matching notes!\n");
        }

        return builder.toString();
    }

    /**
     * Returns a String when the user has removed a new task from the list.
     *
     * @param taskList The tasklist the user has removed from.
     * @param task The task the user has removed.
     */
    public String remove(TaskList taskList, Task task) {
        return "Dino is removing " + task + "..."
            + "\nYou have " + taskList.getTaskSize() + " task" + (taskList.getTaskSize() > 1 ? "s!" : "!");
    }

    /**
     * Returns a String when the user has added a new task to the list.
     *
     * @param note The note the user has added.
     */
    public String add(Note note) {
        return "Dino added: " + note;
    }
}
