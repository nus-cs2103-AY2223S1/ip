package duke.task;

import java.time.LocalDateTime;

import duke.Parser;

public class DeadlineTask extends Task {

    public DeadlineTask(String description, LocalDateTime deadline, boolean isDone) {
        super('D', description, deadline, isDone);
        assert !description.isBlank();
        assert deadline != null;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(),
                super.getTime().format(Parser.DATETIME_OUTPUT_FORMAT));
    }
}
