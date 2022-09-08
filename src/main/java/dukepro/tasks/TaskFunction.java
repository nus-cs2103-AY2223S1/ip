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
     * @return Task.
     */
    public static Task markAsDone(ArrayList<? extends Task> tasks, int n) {
        Task done = tasks.get(n - 1);
        done.markAsDone();
        return done;
    }
}
