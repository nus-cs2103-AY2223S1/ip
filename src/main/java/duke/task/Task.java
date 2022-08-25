package duke.task;

import duke.util.DukeException;

import java.util.Objects;

public class Task {
    protected String action;
    protected boolean isDone;
    protected String date;

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
