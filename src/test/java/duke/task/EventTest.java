package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void testEventToString() {
        assertEquals("[E] [ ] testing (at: Dec 12 2022)", new Event("testing",
                LocalDate.of(2022, 12, 12)).toString());
    }
    @Test
    public void testSaveStringFormat() {
        assertEquals("E | 0 | testing | Dec 12 2022", new Event("testing",
                LocalDate.of(2022, 12, 12)).saveStringFormat());
    }

    @Test
    public void markEvent_success() {
        Event event = new Event("Birthday", LocalDate.of(2022, 9, 9));
        event.markDone();
        assertEquals("[E] [X] Birthday (at: Sep 9 2022)", event.toString());
    }

    @Test
    public void unmarkEvent_success() {
        Event event = new Event("Birthday", LocalDate.of(2022, 9, 9));
        event.markUndone();
        assertEquals("[E] [ ] Birthday (at: Sep 9 2022)", event.toString());
    }

}
