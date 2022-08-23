package duke.test;

import duke.task.Event;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getEventAtTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2022", formatter);
        Event e = new Event("event", date);
        assertEquals("2022-01-01", e.getEventAt());
    }

    @Test
    public void getDescriptionTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse("01/01/2022", formatter);
        Event e = new Event("hello world", date);
        assertEquals("hello world", e.getDescription());
    }
}
