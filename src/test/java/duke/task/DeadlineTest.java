package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineToString() {
            assertEquals("[D] [ ] testing (by: Dec 12 2022)", new Deadline("testing"
                    , LocalDate.of(2022,12, 12)).toString());
    }
    @Test
    public void testSaveStringFormat() {
        assertEquals("D | 0 | testing | Dec 12 2022", new Deadline("testing"
                , LocalDate.of(2022,12,12)).saveStringFormat());
    }
}
