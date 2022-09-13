import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void add_deadline_success() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("[D][ ] Tutorial 1 (by: Aug 25 2022)", deadline.toString());
    }

    @Test
    public void mark_deadline_success() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        assertEquals("[D][X] Tutorial 1 (by: Aug 25 2022)", deadline.toString());
    }

    @Test
    public void unmark_deadline_success() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        deadline.unmark();
        assertEquals("[D][ ] Tutorial 1 (by: Aug 25 2022)", deadline.toString());
    }

    @Test
    public void save_unmarkedDeadline_success() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("D | 0 | 0 | Tutorial 1 | 2022-08-25", deadline.saveTask());
    }

    @Test
    public void save_markedDeadline_success() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        assertEquals("D | 1 | 0 | Tutorial 1 | 2022-08-25", deadline.saveTask());
    }

    @Test
    public void setHighPriority_deadline_success() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.setHighPriority();
        assertEquals("[D][ ][!] Tutorial 1 (by: Aug 25 2022)", deadline.toString());
    }
}
