package functional;

import technical.SaveLine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    /**
     * Construct functional.Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    public Deadline(SaveLine line) {
        super(line);
        deadline = LocalDateTime
            .parse(line.getValue("deadline"), DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss"));
    }

    /**
     * Shows the deadline name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[D]%s (by %s)", super.toString(),
            deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
    }

    @Override
    public SaveLine toData() {
        SaveLine ret = super.toData();
        ret.setInfoType("deadline");
        ret.addNameData("deadline", deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
        return ret;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Deadline) {
            Deadline rhsDeadline = (Deadline) rhs;
            return toData().equals(rhsDeadline.toData());
        }
        return false;
    }
}
