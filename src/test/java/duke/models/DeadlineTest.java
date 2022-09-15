package duke.models;

import org.junit.jupiter.api.Test;

import duke.DateParser;

/**
 * JUnit test class for {@code Deadline} class.
 */

public class DeadlineTest {

    /**
     * Test the toString() method in {@code Deadline}
     */
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("return book", DateParser.parseDate("2020-08-08"));
        assert deadline.toString().equals("[D][ ] return book (by: Aug 8 2020)");
    }

    /**
     * Tests the markAsDone() method in {@code Deadline}
     */
    @Test
    public void testMarkDone() {
        Deadline deadline = new Deadline("return book", DateParser.parseDate("2020-08-08"));
        deadline.markAsDone();
        assert deadline.toString().equals("[D][X] return book (by: Aug 8 2020)");
    }

    /**
     * Tests the markAsUndone() method in {@code Deadline}
     */
    @Test
    public void testMarkAsNotDone() {
        Deadline deadline = new Deadline("return book", DateParser.parseDate("2020-08-08"));
        deadline.markAsDone();
        deadline.markAsNotDone();
        assert deadline.toString().equals("[D][ ] return book (by: Aug 8 2020)");
    }

    /**
     * Test the postpone function in {@code Deadline}
     */
    @Test
    public void testPostpone() {
        Deadline deadline = new Deadline("return book", DateParser.parseDate("2020-08-08"));
        deadline.postponeTask();
        assert deadline.toString().equals("[D][ ] return book (by: Aug 9 2020)");
    }
}
