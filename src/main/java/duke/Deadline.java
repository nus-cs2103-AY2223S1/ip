package duke;

import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate due;

    public Deadline(String desc, char taskType) {
        super(desc, taskType);
        due = Parser.formatDate(desc);
    }

    public Deadline(String desc, char completed, char taskType) {
        super(desc,completed, taskType);
        due = Parser.formatDate(desc);
    }

    public Deadline(String desc) {
        super(desc);
        due = Parser.formatDate(desc);
    }

    protected Deadline performTask() {
        return new Deadline(this.getDesc(), 'X', this.getTaskType());
    }

    protected Deadline undoTask() {
        return new Deadline(this.getDesc(), this.getTaskType());
    }
}