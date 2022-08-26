package duke.task;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void test() throws DukeException {
        Deadline task = new Deadline("testing", LocalDate.parse("2022-08-26"));
        task.doing();
        assertEquals("[D][X] testing (by: Aug 26 2022)", task.toString());

        assertEquals(true, task.isDone());

        task.undo();
        assertEquals("[D][ ] testing (by: Aug 26 2022)", task.toString());

        assertEquals("testing", task.getDescription());

        assertEquals(false, task.isDone());
    }
}
