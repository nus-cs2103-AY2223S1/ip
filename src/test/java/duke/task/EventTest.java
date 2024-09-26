package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getTaskTest() {
        LocalDate date = LocalDate.parse("2019-10-09");
        Event event = new Event("hello", date);


        assertEquals("[E] [ ] hello (at: Oct 09 2019)", event.getTask());
    }
}
