package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate due;

    public Deadline(String desc, char taskType) {
        super(desc, taskType);
        due = getDue(desc);
    }

    public Deadline(String desc, char completed, char taskType) {
        super(desc,completed, taskType);
        due = getDue(desc);
    }

    public Deadline(String desc) {
        super(Parser.formatDate(desc));
        due = getDue(desc);
    }

    private LocalDate getDue(String desc) {
        int index = desc.indexOf('/');
        if (index > 0) {
            return LocalDate.parse(desc.substring(index + 1), DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        }
        return LocalDate.now();
    }

    protected Deadline performTask() {
        return new Deadline(this.getDesc(), 'X', this.getTaskType());
    }

    protected Deadline undoTask() {
        return new Deadline(this.getDesc(), this.getTaskType());
    }
}