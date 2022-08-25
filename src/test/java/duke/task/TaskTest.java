package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;


public class TaskTest {

    @Test
    public void testTask() {
        LocalTime localTime = LocalTime.parse("14:01:02");
        LocalDate localDate = LocalDate.parse("2020-12-12");
        Task task = new Task("minum burger", true, localDate, localTime);

        assertEquals(task.isMarked(), true);

        assertEquals(task.getOutputDateAndTime(), "Dec 12 2020, 14:01:02)");
    }

}
