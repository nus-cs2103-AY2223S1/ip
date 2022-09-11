package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineToStringTest() {
        Deadline d = new Deadline("iP Week 3", false, LocalDate.parse("2022-08-21"));
        assertEquals(" [D][ ] iP Week 3 (by: Aug 21 2022)", d.toString());
    }

    @Test
    public void markDeadlineTest() {
        Deadline d = new Deadline("iP Week 3", false, LocalDate.parse("2022-08-21"));
        d.markAsDone();
        assertEquals(" [D][X] iP Week 3 (by: Aug 21 2022)", d.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        Deadline d = new Deadline("iP Week 3", true, LocalDate.parse("2022-08-21"));
        d.markAsUndone();
        assertEquals(" [D][ ] iP Week 3 (by: Aug 21 2022)", d.toString());
    }

    @Test
    public void deadlineSaveStringFormatTest1() {
        Deadline d = new Deadline("iP Week 3", false, LocalDate.parse("2022-08-21"));
        assertEquals("D | 0 | iP Week 3 | 2022-08-21", d.saveStringFormat());
    }

    @Test
    public void deadlineSaveStringFormatTest2() {
        Deadline d = new Deadline("iP Week 3", true, LocalDate.parse("2022-08-21"));
        assertEquals("D | 1 | iP Week 3 | 2022-08-21", d.saveStringFormat());
    }

}
