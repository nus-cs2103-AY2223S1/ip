package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        Deadline deadline = new Deadline();

        deadline.setText("Test toString");
        LocalDate date = LocalDate.of(2022, 8, 23);
        deadline.setDetails(date);
        String dateString = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        assertEquals(deadline.toString(), "[D][ ] Test toString (by: " + dateString + ")");

        deadline.setComplete(true);
        assertEquals(deadline.toString(), "[D][X] Test toString (by: " + dateString + ")");
    }

    @Test
    public void taskTypeTest() {
        Deadline deadline = new Deadline();
        assertEquals(deadline.getTaskType(), Task.TaskType.Deadline);
    }

    @Test
    public void serializeTest() {
        Deadline deadline = new Deadline();
        deadline.setText("Test toString");
        deadline.setComplete(true);
        deadline.setDetails(LocalDate.of(3000, 1, 1));
        System.out.println(deadline.serialize());
        assertEquals(deadline, Task.deserialize(deadline.serialize()));
    }

}
