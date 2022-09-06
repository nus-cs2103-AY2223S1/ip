package duke.modules.todos;

import duke.MessagefulException;

import java.util.List;

import static java.lang.String.format;

/**
 * Parent class for tasks.
 */
public abstract class Task {
    private final String name;
    private boolean done;

    /**
     * Constructor
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Constructor
     *
     * @param name The name of the task
     * @param done Whether the task is done.
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Sets whether the task is done.
     *
     * @param done Whether the task is done.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    public static final String typeCode = "B";
    private final String doneSer = "X";
    private final String undoneSer = "-";

    /**
     * Packs the task's data into a List.
     *
     * @return The packed data.
     */
    public List<String> flatPack() {
        return List.of(typeCode, this.done ? doneSer : undoneSer, this.name);
    }

    /**
     * Unpacks the task's data from a List.
     *
     * @param l The packed data.
     */
    public Task(List<? extends String> l) {
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

    /**
     * Unpacks the task's data into a task of the correct subtype.
     *
     * @param line The packed data.
     * @return The task described by the packed data.
     * @throws MessagefulException if something goes wrong.
     */
    static Task fromFlatpack(List<String> line) throws MessagefulException {
        switch (line.get(0)) {
        case typeCode:
        case Todo.typeCode:
            return new Todo(line);
        case Deadline.typeCode:
            return new Deadline(line);
        case Event.typeCode:
            return new Event(line);
        default:
            throw new MessagefulException(
                    "unknown task type",
                    "Uh oh! I cannot load the task list.");
        }
    }

    @Override
    public String toString() {
        return format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
