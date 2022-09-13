package duke.modules.todos;

import duke.MessagefulException;

import java.util.List;

import static java.lang.String.format;

/**
 * Parent class for tasks.
 */
public abstract class Task {
    private final String name;
    private boolean isDone;

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
     * @param name   The name of the task
     * @param isDone Whether the task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Sets whether the task is done.
     *
     * @param done Whether the task is done.
     */
    public void setDone(boolean done) {
        this.isDone = done;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * The unique code of the task type. Used to identify the type of task being unpacked.
     */
    public static final String TYPE_CODE = "B";
    private final String doneSerialization = "X";
    private final String undoneSerialization = "-";

    /**
     * Packs the task's data into a List.
     *
     * @return The packed data.
     */
    public List<String> flatPack() {
        return List.of(TYPE_CODE, this.isDone ? doneSerialization : undoneSerialization, this.name);
    }

    /**
     * Unpacks the task's data from a List.
     *
     * @param l The packed data.
     */
    public Task(List<? extends String> l) {
        this.name = l.get(2);

        final String doneStr = l.get(1);
        if (doneSerialization.equals(doneStr)) {
            this.isDone = true;
        } else if (undoneSerialization.equals(doneStr)) {
            this.isDone = false;
        } else {
            throw new IllegalArgumentException("Invalid done value found while unpacking task: " + doneStr);
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
        case TYPE_CODE:
        case Todo.TYPE_CODE:
            return new Todo(line);
        case Deadline.TYPE_CODE:
            return new Deadline(line);
        case Event.TYPE_CODE:
            return new Event(line);
        default:
            throw new MessagefulException(
                    "unknown task type",
                    "Uh oh! I cannot load the task list.");
        }
    }

    @Override
    public String toString() {
        return format("[%s] %s", this.isDone ? "X" : " ", this.name);
    }
}
