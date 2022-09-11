package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void eventToStringTest() {
        Event e = new Event("iP Week 3", false, LocalDate.parse("2022-08-21"));
        assertEquals(" [E][ ] iP Week 3 (at: Aug 21 2022)", e.toString());
    }

    @Test
    public void markEventTest() {
        Event e = new Event("iP Week 3", false, LocalDate.parse("2022-08-21"));
        e.markAsDone();
        assertEquals(" [E][X] iP Week 3 (at: Aug 21 2022)", e.toString());
    }

    @Test
    public void unmarkEventTest() {
        Event e = new Event("iP Week 3", true, LocalDate.parse("2022-08-21"));
        e.markAsUndone();
        assertEquals(" [E][ ] iP Week 3 (at: Aug 21 2022)", e.toString());
    }

    @Test
    public void eventSaveStringFormatTest1() {
        Event e = new Event("iP Week 3", false, LocalDate.parse("2022-08-21"));
        assertEquals("E | 0 | iP Week 3 | 2022-08-21", e.saveStringFormat());
    }

    @Test
    public void eventSaveStringFormatTest2() {
        Event e = new Event("iP Week 3", true, LocalDate.parse("2022-08-21"));
        assertEquals("E | 1 | iP Week 3 | 2022-08-21", e.saveStringFormat());
    }

}
