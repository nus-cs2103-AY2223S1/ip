package blink.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventsTest {

    @Test
    public void addEvent() {
        Task event = new Events("Study", "2022-08-23");
        assertEquals("[E][ ] Study (at: AUGUST 23 2022 TUESDAY)",event.toString());
    }

    @Test
    public void markEvent() {
        Task event = new Events("Study 2", "2022-08-23");
        event.mark();
        assertEquals("[E][X] Study 2 (at: AUGUST 23 2022 TUESDAY)", event.toString());
    }

    @Test
    public void unMarkEvent() {
        Task event = new Events("Study 3", "2022-08-23");
        event.mark();
        event.unMark();
        assertEquals("[E][ ] Study 3 (at: AUGUST 23 2022 TUESDAY)", event.toString());
    }

    @Test
    public void checkEventDate() {
        Task event = new Events("Study 4", "2022-08-23");
        LocalDate date = LocalDate.parse("2022-08-23");
        assertEquals(true, event.checkDate(date));
    }

    @Test
    public void saveEvent() {
        Task event = new Events("Study 5", "2022-08-23");
        String expected = "E |0| Study 5 | 2022-08-23\n";
        assertEquals(expected, event.saveString());
    }
}
