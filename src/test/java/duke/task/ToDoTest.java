package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void testNormal1() {
        ToDo todo = new ToDo("todo test todo");
        assertEquals(todo.toString(), "[T] [ ] test todo");
    }

    @Test
    public void testNormal2() {
        ToDo todo = new ToDo("todo 1");
        assertEquals(todo.toString(), "[T] [ ] 1");
    }

    //Note: just the blank command itself can never be a given command.
}
