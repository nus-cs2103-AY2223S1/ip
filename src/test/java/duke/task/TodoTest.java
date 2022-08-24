package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void test1() {
        Todo test = new Todo("Assignment 1");
        assertEquals("[T] [ ] Assignment 1", test.toString());
    }

    @Test
    public void markTodoTest() {
        Todo test = new Todo("Project X");
        test.mark();
        assertEquals("[T] [X] Project X", test.toString());
    }
}
