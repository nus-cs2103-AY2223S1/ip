package byu.task;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import byu.util.Ui;

/**
 *  A task with a deadline.
 */
public class Deadline extends Task {

    public static final String SYMBOL = "D";
    private static final String PRINT_FORMAT = "[D]%s (by: %s)";
    private static final String WRITE_FORMAT = "D | %s | %s | %s\n";

    private final LocalDateTime dateTime;

    /**
     * Creates a task with a deadline.
     */
    public Deadline(String name, LocalDateTime dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public void write(FileWriter fileWriter) throws IOException {
        String line = String.format(Deadline.WRITE_FORMAT, this.getDoneValue(), this.getName(), this.dateTime);
        fileWriter.write(line);
    }

    @Override
    public String toString() {
        return String.format(Deadline.PRINT_FORMAT, super.toString(),
                this.dateTime.format(Ui.PRINT_DATE_TIME_FORMATTER));
    }

    @Override
    public boolean equals(Task task) {
        if (task == null) {
            return false;
        } else if (!(task instanceof Deadline)) {
            return false;
        } else if (!this.getName().equals(task.getName())) {
            return false;
        } else {
            // to compare the dateTimes of the two events
            Deadline deadline = (Deadline) task;
            return this.dateTime.isEqual(deadline.dateTime);
        }
    }
}
