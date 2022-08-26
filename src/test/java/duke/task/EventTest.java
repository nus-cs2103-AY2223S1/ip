package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testEventToString() {
        assertEquals("[E] [ ] testing (at: Dec 12 2022)", new Event("testing"
                , LocalDate.of(2022,12,12)).toString());
    }
    @Test
    public void testSaveStringFormat() {
        assertEquals("E | 0 | testing | Dec 12 2022", new Event("testing"
                , LocalDate.of(2022,12,12)).saveStringFormat());
    }
}
