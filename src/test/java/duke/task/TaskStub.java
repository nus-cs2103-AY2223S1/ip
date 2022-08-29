package duke.task;

import java.time.LocalDate;

/**
 * Task Stub for Duke application testing.
 *
 * @author Farrel Dwireswara Salim
 */
public class TaskStub extends Task {
    protected TaskStub() {
        super("sample");
    }

    @Override
    public boolean isOnGivenDate(LocalDate ... selectedDates) {
        return false;
    }
}
