package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;


public class EventTest {
    @Test
    public void getTaskTest() {
        LocalDate date = LocalDate.parse("2019-10-09");
        Event event = new Event("hello", date);


        assertEquals("[E] [ ] hello (at: tomorrow)", event.getTask());
    }
}
