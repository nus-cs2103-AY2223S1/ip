package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TodoTest {
    @Test
    public void testDescription() {
        assertEquals(new Todo("run").toString(), "[T][ ] run");
        assertEquals(new Todo("create  test methods for class").toString(),
                "[T][ ] create  test methods for class");
    }

    @Test
    public void testMarking() {
        Todo todo = new Todo("run");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T][X] run");
        todo.markAsDone();
        assertEquals(todo.toString(), "[T][X] run");
        todo.markAsNotDone();
        assertEquals(todo.toString(), "[T][ ] run");
        todo.markAsNotDone();
        assertEquals(todo.toString(), "[T][ ] run");
    }
}
