package functional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import technical.SaveLine;

/**
 * Class for tasks with a deadline
 * @author Nicholas Patrick
 */
public class Deadline extends Task {
    private static final String DEADLINE_INFOTYPE = "deadline";
    private static final String DEADLINE_DEADLINE_LABEL = "deadline";
    private final LocalDateTime deadline;

    /**
     * Construct functional.Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Construct a task with a deadline from a SaveLine. If the argument is
     * invalid, an error may or may not be thrown.
     *
     * @param line The SaveLine containing necessary information.
     */
    public Deadline(SaveLine line) {
        super(line);
        System.out.println(line.getValue(DEADLINE_DEADLINE_LABEL));
        deadline = LocalDateTime
            .parse(line.getValue(DEADLINE_DEADLINE_LABEL),
            DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss"));
    }

    /**
     * Shows the deadline name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(),
            deadline.format(DateTimeFormatter
            .ofPattern("d MMM yyyy 'at' HH:mm:ss")));
    }

    /**
     * Turns the task into a SaveLine, so it's ready to be saved. Can also be
     * used to compare two tasks.
     *
     * @return A SaveLine with the data associated with the task.
     */
    @Override
    public SaveLine toData() {
        SaveLine ret = super.toData();
        ret.setInfoType(DEADLINE_INFOTYPE);
        ret.addKeyValue(DEADLINE_DEADLINE_LABEL,
            deadline.format(DateTimeFormatter
            .ofPattern("d MMM yyyy 'at' HH:mm:ss")));
        return ret;
    }

    @Override
    public boolean equals(Object rhs) {
        if (!(rhs instanceof Deadline)) {
            return false;
        }
        Deadline rhsDeadline = (Deadline) rhs;
        return toData().equals(rhsDeadline.toData());
    }

    @Override
    public LocalDateTime getTime() {
        return deadline;
    }
}
