package command;

import org.junit.jupiter.api.Test;

import task.Deadline;

public class AddDeadlineCommandTest {
    @Test
    public void addDeadline_nullDeadline_doesNothing() {
        Deadline d = null;
        AddDeadlineCommand adc = new AddDeadlineCommand(d);
    }
}
