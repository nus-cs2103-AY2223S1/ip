package duke.modules.todos;

import java.util.List;

import static java.lang.String.format;

/**
 * Parent class for tasks.
 */
public abstract class Task {
    private String name;
    private boolean done;

    /**
     * Constructor
     * @param name The name of the task.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Constructor
     * @param name The name of the task
     * @param done Whether the task is done.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Sets whether the task is done.
     * @param done Whether the task is done.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    private final String typeCode = "B";
    private final String doneSer = "X";
    private final String undoneSer = "-";

    /**
     * Packs the task's data into a List.
     *
     * @return The packed data.
     */
    public List<String> flatpack() {
        return List.of(typeCode, this.done ? doneSer : undoneSer, this.name);
    }

    /**
     * Unpacks the task's data from a List.
     *
     * @param l The packed data.
     */
    public Task(List<? extends String> l) {
        if (l.get(0) != typeCode) {
            throw new IllegalArgumentException("Trying to hydrate non-task as task: " + l);
        }

        this.name = l.get(2);

        final String doneStr = l.get(1);
        if (doneSer.equals(doneStr)) {
            this.done = true;
        } else if (undoneSer.equals(doneStr)) {
            this.done = false;
        } else {
            throw new IllegalArgumentException("Invalid done value found while hydrating task: " + doneStr);
        }
    }

    @Override
    public String toString() {
        return format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
