import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;

public class DeadlineTest {
    @Test
    public void addDeadlineTest() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("[D][ ] Tutorial 1 (by: Aug 25 2022)", deadline.toString());
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        assertEquals("[D][X] Tutorial 1 (by: Aug 25 2022)", deadline.toString());
    }

    @Test
    public void unmarkDeadlineTest() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        deadline.unmark();
        assertEquals("[D][ ] Tutorial 1 (by: Aug 25 2022)", deadline.toString());
    }

    @Test
    public void saveDeadlineTest() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        assertEquals("D | 0 | Tutorial 1 | 2022-08-25", deadline.saveTask());
    }

    @Test
    public void saveDeadlineTest2() {
        Deadline deadline = new Deadline("Tutorial 1", LocalDate.parse("2022-08-25"));
        deadline.mark();
        assertEquals("D | 1 | Tutorial 1 | 2022-08-25", deadline.saveTask());
    }
}
