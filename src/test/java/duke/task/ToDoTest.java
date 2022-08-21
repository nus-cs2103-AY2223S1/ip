package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void inputCommand_normalToDo_toDoReturned() {
        ToDo todo = new ToDo("todo test todo");
        assertEquals(todo.toString(), "[T] [ ] test todo");
    }

    @Test
    public void inputCommand_toDoWithNumberOnly_toDoReturned() {
        ToDo todo = new ToDo("todo 1");
        assertEquals(todo.toString(), "[T] [ ] 1");
    }

    //Note: just the blank command itself can never be a given command.
}
