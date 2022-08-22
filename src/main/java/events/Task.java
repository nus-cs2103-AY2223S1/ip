package events;

import java.util.Arrays;

public class Task {
    private final String text;
    private Boolean isComplete;

    public Task(String text) {
        this.text = text;
        this.isComplete = false;
    }

    /**
     * Sets the task to be complete.
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Sets the task to be incomplete.
     */
    public void setIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return this.isComplete
                ? String.format("[X] %s", this.text)
                : String.format("[ ] %s", this.text);
    }

    public static Task of(String type, String done, String text, String extraText) throws Exception {
        switch (type) {
            case "E":
                Event newEvent = new Event(text, extraText);
                if (done == "1") {
                    newEvent.setComplete();
                }
                return newEvent;

            case "D":
                Deadline newDeadline = new Deadline(text, extraText);
                if (done == "1") {
                    newDeadline.setComplete();
                }
                return newDeadline;

            case "T":
                Todo newTodo = new Todo(text);
                if (done == "1") {
                    newTodo.setComplete();
                }
                return newTodo;

            default:
                throw new Exception("Impossible for task to be not e d or t");
        }
    }

    public String exportString() {
        return String.format(
                "%s%d%s%s%s",
                "@@@",
                this.isComplete ? 1 : 0,
                "@@@",
                this.text,
                "@@@");
    }
}
