package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



public class EventTaskTest {
    @Test
    public void toStringTest() {
        Task eventTask = new EventTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("[E][ ] read book (at: Aug 22 2019)", eventTask.toString());
    }

    @Test
    public void toSaveStringTest() {
        Task eventTask = new EventTask("read book", LocalDate.parse("2019-08-22"));
        assertEquals("E | 0 | read book | 2019-08-22", eventTask.toSaveString());
    }
}
