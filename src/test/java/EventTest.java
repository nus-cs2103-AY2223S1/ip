import org.junit.jupiter.api.Test;
import duke.tasks.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {


    @Test
    public void newEventTest() {
        assertEquals(new Event(
                "Party", LocalDate.parse("2022-12-13").
                        format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).toString(),
                "[E][ ] Party (at: Dec 13 2022)");
        assertEquals(new Event(
                        "Wedding Ceremony", LocalDate.parse("2012-06-01").
                        format(DateTimeFormatter.ofPattern("MMM dd yyyy"))).toString(),
                "[E][ ] Wedding Ceremony (at: Jun 01 2012)");
    }


    @Test
    public void markEventTest() {
        String date = LocalDate.parse("2022-12-13").format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        Event Party = new Event("Birthday Party", date);
        Party.mark();

        assertEquals(Party.toString(), "[E][X] Birthday Party (at: Dec 13 2022)");
    }

    @Test
    public void unmarkEventTest() {
        String date = LocalDate.parse("2022-12-13").format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        Event Party = new Event("Birthday Party", date);
        Party.mark();
        Party.unmark();

        assertEquals(Party.toString(), "[E][ ] Birthday Party (at: Dec 13 2022)");
    }
}
