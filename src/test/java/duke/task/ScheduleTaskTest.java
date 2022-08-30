package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.UnexpectedDateTimeFormatException;

class ScheduleTaskStub extends ScheduleTask {
    public ScheduleTaskStub(String description, String dateTime) throws UnexpectedDateTimeFormatException {
        super(description, dateTime);
    }

    public ScheduleTaskStub(String description, String dateTime, boolean done) {
        super(description, dateTime, done);
    }

    @Override
    public String toString() {
        return description + " " + showDateTime();
    }

}

class ScheduleTaskTest {

    @Test
    public void constructor_acceptableConstructor_success() throws UnexpectedDateTimeFormatException {
        ScheduleTaskStub dukeTask = new ScheduleTaskStub("read book", "28/08/2022 1800");
        assertEquals("read book Aug 28 2022 18:00", dukeTask.toString());
        ScheduleTaskStub storageTask = new ScheduleTaskStub("read book", "Aug 28 2022 18:00", false);
        assertEquals("read book Aug 28 2022 18:00", storageTask.toString());
    }

    @Test
    public void constructor_unacceptableConstructor_exceptionThrown() {
        try {
            ScheduleTaskStub dukeTask = new ScheduleTaskStub("read book", "28/8/2022 1800");
            fail();
        } catch (UnexpectedDateTimeFormatException e) {
            assertEquals("☹ OOPS!!! Wrong date and time format! Please give in the format DD/MM/YYYY HHmm",
                    e.getMessage());
        }
    }
}
