package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {

    @Test
    public void getDateTest () {
        LocalDate date = LocalDate.parse("2019-10-11");
        Event e = new Event("Test1",date);
        assertEquals("2019-10-11", e.getDate().toString());
    }

    @Test
    public void getDescriptionTest () {
        LocalDate date = LocalDate.parse("2019-10-16");
        Event e = new Event("Test1",date);
        assertEquals("[E]" + "[ ] " + "Test1" + "(" + date + ")", e.getDescription());
    }
}
