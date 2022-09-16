package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDo;

public class ReminderTest {
    @Test
    public void testCountDown() throws DukeException {
        Reminder reminder = new Reminder();
        Task todo = new ToDo("drink water ",
                LocalDate.parse("2024-09-15"), LocalTime.parse("01:01:00"));

        String actualCountDown = "1 Year, 11 Month, 29 Day, 3 Hour, 54 Minute ";
        String computedCountDown = reminder.getCountDown(todo);
        assertEquals(actualCountDown, computedCountDown);
    }

}
