package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void addDeadlineTest() {
        Deadline deadline = new Deadline("Read Book", LocalDate.parse("2069-06-09"));
        assertEquals("[D][ ] Read Book (by: Jun 9 2069)", deadline.toString());
    }

    @Test
    public void markDeadlineTest() {
        Deadline deadline = new Deadline("Read Book", LocalDate.parse("2069-06-09"));
        deadline.markAsDone();
        assertEquals("[D][X] Read Book (by: Jun 9 2069)", deadline.toString());
    }

    @Test
    public void UnmarkDeadlineTest() {
        Deadline deadline = new Deadline("Read Book", LocalDate.parse("2069-06-09"));
        deadline.markAsDone();
        deadline.markAsUndone();
        assertEquals("[D][ ] Read Book (by: Jun 9 2069)", deadline.toString());
    }

    @Test
    public void saveDeadlineTest() {
        Deadline deadline = new Deadline("Read Book", LocalDate.parse("2069-06-09"));
        assertEquals("D | 0 | Read Book | 2069-06-09", deadline.save());
    }

    @Test
    public void getTimeTest() {
        Deadline deadline = new Deadline("Read Book", LocalDate.parse("2069-06-09"));
        assertEquals(LocalDate.parse("2069-06-09"), deadline.getTime());
    }

}
