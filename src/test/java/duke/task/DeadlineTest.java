package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void testDeadlineToString() {
        assertEquals("[D] [ ] testing (by: Dec 12 2022)", new Deadline("testing",
                 LocalDate.of(2022, 12, 12)).toString());
    }
    @Test
    public void testSaveStringFormat() {
        assertEquals("D | 0 | testing | Dec 12 2022", new Deadline("testing",
                LocalDate.of(2022, 12, 12)).saveStringFormat());
    }

    @Test
    public void markDeadline_success() {
        Deadline deadline = new Deadline("Homework", LocalDate.of(2022, 9, 9));
        deadline.markDone();
        assertEquals("[D] [X] Homework (by: Sep 9 2022)", deadline.toString());
    }

    @Test
    public void unmarkEvent_success() {
        Deadline deadline = new Deadline("Homework", LocalDate.of(2022, 9, 9));
        deadline.markUndone();
        assertEquals("[D] [ ] Homework (by: Sep 9 2022)", deadline.toString());
    }
}
