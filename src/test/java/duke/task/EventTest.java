package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;


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
        assertEquals("[E]" + "[ ] " + "Test1" + "(" + date + ")", e.getDescription());
    }
}
