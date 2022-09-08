package duke.task;

import duke.DukeException;

public class Task {
    private String content;
    private Boolean isDone;

    final static String DATE_OUTPUT_FORMAT = "MMM dd yyyy hh:mm a";
    final static String DATE_INPUT_FORMAT = "yyyy-MM-dd HH:mm";


    public Task(String content) throws DukeException {
        if (content == "") {
            throw new DukeException("You need to provide a content");
        }
        this.content = content;
        this.isDone = false;
    }

    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public String markIsDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task is already done");
        } else {
            this.isDone = true;
            return "OK, I've marked this task as done: " + this;
        }
    }

    public String unmarkIsDone() throws DukeException {
        if (isDone) {
            this.isDone = false;
            return "OK, I've marked this task as not done yet: " + this;
        } else {
            throw new DukeException("Task is not done");
        }
    }

    public boolean isContain(String input) {
        return content.contains(input);
    }

    @Override
    public String toString() {
        String newString = String.format("[%s] %s", isDone ? "X" : " ", content);
        return newString;
    }

    public String toSaveString() {
        String saveString = String.format("%s|%s", isDone ? "1" : "0", content);
        return saveString;
    }
}
