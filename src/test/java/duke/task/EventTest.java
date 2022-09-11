package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void getDateTest() {
        LocalDate date = LocalDate.parse("2019-10-11");
        Event e = new Event("Test1", date, 'X');
        assertEquals("2019-10-11", e.getDate().toString());
    }

    @Test
    public void getDescriptionTest() {
        LocalDate date = LocalDate.parse("2019-10-16");
        Event e = new Event("Test1", date, 'X');
        assertEquals("[E]" + "[X] " + "Test1" + "(" + date + ")", e.getDescription());
    }
}
