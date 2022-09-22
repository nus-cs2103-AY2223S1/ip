package dukepro.tasks;

import java.util.ArrayList;

/**
 * Class for TaskFunction
 */
public class TaskFunction {
    /**
     * This function marks a particular
     * task in the ArrayList as done.
     *
     * @param tasks The ArrayList.
     * @param n The index of task to
     *          be marked as done.
     * @param isDone Indicates whether to
     *               mark task as done or not.
     * @return Task.
     */
    public static Task markAsDone(ArrayList<? extends Task> tasks, int n, boolean isDone) {
        Task done = tasks.get(n - 1);
        if (isDone) {
            done.markAsDone();
        } else {
            done.markAsUndone();
        }
        return done;
    }
}
