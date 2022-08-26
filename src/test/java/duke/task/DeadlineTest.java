package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DeadlineTest class to test features of the Deadline task.
 */
public class DeadlineTest {

    /**
     * Tests the toString method.
     */
    @Test
    public void deadlineToStringTest() {
        assertEquals(new Deadline("read book", "2022-02-15").toString()
                , "[D][ ] read book (by: 15-02-2022)");
    }

    /**
     * Tests the toFile method.
     */
    @Test
    public void deadlineToFileTest() {
        assertEquals(new Deadline("read book", "2022-02-15").toFile()
                , "D | 0 | read book | 15-02-2022\n");
    }

    /**
     * Tests the setDone method by invoking toString after.
     */
    @Test
    public void deadlineDoneToStringTest() {
        Deadline test = new Deadline("read book", "2022-02-15");
        test.setDone();
        assertEquals(test.toString(), "[D][X] read book (by: 15-02-2022)");
    }
}