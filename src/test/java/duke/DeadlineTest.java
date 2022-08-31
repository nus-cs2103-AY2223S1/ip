package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * DeadlineTest class to test Deadline object methods.
 */
public class DeadlineTest {

    /**
     * Tests for string representation of Deadline object.
     */
    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: Mar 21 2022)", new Deadline("return book", "2022-03-21").toString());
    }
}
