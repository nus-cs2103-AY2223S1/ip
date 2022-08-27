package duke.task;

import java.util.Objects;

import duke.util.DukeException;

/**
 * Task Class to store information of task.
 * @author Jicson Toh
 */
public class Task {
    protected String action;
    protected boolean isDone;
    protected String date;

    /**
     * Creates a new task object.
     * @param action user input action to be done.
     * @param isDone check is completed.
     * @param date date to be completed.
     * @throws DukeException if action is not specified.
     */
    public Task(String action, boolean isDone, String date) throws DukeException {
        if (Objects.equals(action, "")) {
            throw new DukeException();
        }
        this.action = action;
        this.isDone = isDone;
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public boolean getDone() {
        return isDone;
    }

    /**
     * Checks if the task contains the keyword.
     * @param keyword input keyword.
     * @return true if contains the keyword.
     */
    public boolean matchKeyword(String keyword) {
        String[] words = action.split(" ");
        for (String word : words) {
            if (word.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    public String getDate() {
        return date;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUnDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return ("[" + (isDone ? "X" : " ") + "] " + action);
    }
}
