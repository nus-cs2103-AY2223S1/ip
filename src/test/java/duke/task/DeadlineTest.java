package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Task task = new Deadline("assignment", LocalDate.parse("2022-03-14"));
        assertEquals("[D][ ] assignment (by: Mar 14 2022)", task.toString());
        task.markAsDone();
        assertEquals("[D][X] assignment (by: Mar 14 2022)", task.toString());
    }
}
