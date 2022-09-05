/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * public class DeadlineTest to test for class Deadline.
 */
public class DeadlineTest {
    @Test
    public void test1() {
        Deadline test = new Deadline("Assignment 1", LocalDate.parse("2022-03-13"));
        assertEquals("[D] [ ] Assignment 1 (Priority 0) (by: 2022-03-13)", test.toString());
    }

    @Test
    public void markTodoTest() {
        Deadline test = new Deadline("Project X", LocalDate.parse("2022-03-13"));
        test.mark();
        assertEquals("[D] [X] Project X (Priority 0) (by: 2022-03-13)", test.toString());
    }
}
