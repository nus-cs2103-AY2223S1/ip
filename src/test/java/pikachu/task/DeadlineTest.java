package pikachu.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



/**
 * Represents a test to test deadline class objects. A <code>DeadlineTest</code> object corresponds to
 * a test for deadline class objects.
 */
public class DeadlineTest {

    /**
     * Tests the correctness of name of deadline tasks.
     */
    @Test
    public void getName_rightName() {
        assertEquals("D", new Deadline("", LocalDate.now()).getName());
    }

    /**
     * Tests the correctness of timing of deadline tasks.
     */
    @Test
    public void getTiming_rightTiming() {
        assertEquals("6 July 2022", new Deadline("", LocalDate.of(2022, 7, 6)).getTiming());
    }
}
