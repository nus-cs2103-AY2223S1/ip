package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {
    @Test
    public void ToStringTest() {
        Task eventTask = new EventTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("[E][ ] read book (at: Aug 22 2019)", eventTask.toString());
    }

    @Test
    public void ToSaveStringTest() {
        Task eventTask = new EventTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("E | 0 | read book | 2019-08-22", eventTask.toSaveString());
    }
}