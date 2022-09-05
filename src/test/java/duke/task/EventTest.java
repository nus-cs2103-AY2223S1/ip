/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void test1() {
        Event test = new Event("Assignment 1", LocalDate.parse("2022-03-13"));
        assertEquals("[E] [ ] Assignment 1 (Priority 0) (at: 2022-03-13)", test.toString());
    }

    @Test
    public void markTodoTest() {
        Event test = new Event("Project X", LocalDate.parse("2022-03-13"));
        test.mark();
        assertEquals("[E] [X] Project X (Priority 0) (at: 2022-03-13)", test.toString());
    }
}
