/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void test1() {
        Todo test = new Todo("Assignment 1");
        assertEquals("[T] [ ] Assignment 1 (Priority 0)", test.toString());
    }

    @Test
    public void markTodoTest() {
        Todo test = new Todo("Project X");
        test.mark();
        assertEquals("[T] [X] Project X (Priority 0)", test.toString());
    }
}
