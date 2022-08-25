package duke.task;

import duke.DukeException;

public class Task {
    private String title;
    private Boolean isDone;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public void markIsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task is already done");
        } else {
            System.out.println("OK, I've marked this task as done: ");
            this.isDone = true;
            System.out.println(this);
        }
    }

    public void unmarkIsDone() throws DukeException {
        if (isDone) {
            System.out.println("OK, I've marked this task as not done yet: ");
            this.isDone = false;
            System.out.println(this);
        } else {
            throw new DukeException("Task is not done");
        }
    }

    public boolean isContain(String input) {
        return title.contains(input);
    }

    @Override
    public String toString() {
        String newString = String.format("[%s] %s", isDone ? "X" : " ", title);
        return newString;
    }

    public String toSaveString() {
        String saveString = String.format("%s|%s", isDone ? "1" : "0", title);
        return saveString;
    }
}
