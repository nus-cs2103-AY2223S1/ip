package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToDo() {
        LocalTime localTime = LocalTime.parse("14:01:02");
        LocalDate localDate = LocalDate.parse("2013-12-12");
        ToDo toDo = new ToDo("makan burger", localDate, localTime);

        assertEquals(toDo.getTaskType(), "T");

        assertEquals(toDo.getTaskName(), "makan burger");
    }

}
