package bob;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void newDeadline() {
        Task task = new Deadline("assignment", LocalDate.parse("2019-12-12"));
        assertEquals("[D][ ] assignment (by: Dec 12 2019)", task.toString());
    }

    @Test
    public void markDeadline() {
        TaskList tasks = new TaskList();
        Task task = new Deadline("math submission", LocalDate.parse("2022-08-28"));
        tasks.addTask(task);
        tasks.markTask(1, true);
        assertEquals("[D][X] math submission (by: Aug 28 2022)", task.toString());
    }
}
