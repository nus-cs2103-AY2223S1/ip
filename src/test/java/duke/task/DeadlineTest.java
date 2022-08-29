package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class DeadlineTest {
    @Test
    public void addToDoTest() {
        Deadline deadline = new Deadline("deadline die /by 2018-08-09");
        assertEquals("[D][ ] die (by: Aug 09 2018)", deadline.toString());
    }

    @Test
    public void markToDoTest() {
        Deadline deadline = new Deadline("deadline die /by 2018-08-09");
        deadline.markAsDone();
        assertEquals("[D][X] die (by: Aug 09 2018)", deadline.toString());
    }
}
