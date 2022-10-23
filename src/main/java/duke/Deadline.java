package duke;

import java.time.LocalDate;

/**
 * Represents a Task that is a Deadline. A Deadline object is Task with a due date.
 */
public class Deadline extends Task {
    private final static char STATUS_COMPLETE = 'X';
    private final LocalDate due;

    public Deadline(String desc, char taskType) {
        super(desc, taskType);
        assert taskType == 'D';
        due = Parser.formatDate(desc);
    }

    public Deadline(String desc, char completed, char taskType) {
        super(desc,completed, taskType);
        due = Parser.formatDate(desc);
    }

    /**
     * Creates a Deadline object based on a given String which describes a Deadline object.
     *
     * @param desc String description of a Deadline.
     */
    public Deadline(String desc) {
        super(desc);
        due = Parser.formatDate(desc);
    }

    /**
     * Creates a Deadline instance that is identical to a given Deadline object, and then marked as complete.
     *
     * @return An identical Deadline object that is marked as complete.
     */
    protected Deadline completeTask() {
        return new Deadline(getDesc(), STATUS_COMPLETE, getTaskType());
    }

    /**
     * Creates a Deadline instance that is identical to a given Deadline object, and then marked as incomplete.
     *
     * @return An identical Deadline object that is marked as incomplete.
     */
    protected Deadline resetTask() {
        return new Deadline(getDesc(), getTaskType());
    }
}