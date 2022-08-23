package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void firstConstructor_normalInput_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321");
        assertEquals("[T][ ] Test 12321", todo.toString());
    }

    @Test
    public void markTodo_unmarkedTodo_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321");
        todo.markAsDone();
        assertEquals("[T][X] Test 12321", todo.toString());
    }

    @Test
    public void secondConstructor_doneInput_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321", "1");
        assertEquals("[T][X] Test 12321", todo.toString());
    }

    @Test
    public void unmarkTodo_markedTodo_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321", "1");
        todo.markAsUndone();
        assertEquals("[T][ ] Test 12321", todo.toString());
    }

    @Test
    public void getDescription_normalInput_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321", "1");
        assertEquals("Test 12321", todo.getDescription());
    }

    @Test
    public void getType_normalInput_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321", "1");
        assertEquals("T", todo.getType());
    }

    @Test
    public void getDate_normalInput_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321", "1");
        assertEquals("Not Applicable", todo.getDate());
    }

    @Test
    public void getDone_doneInput_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321", "1");
        assertEquals(true, todo.getIsDone());
    }

    @Test
    public void getDoneStatus_doneInput_writtenCorrectly() {
        ToDo todo = new ToDo("Test 12321", "1");
        assertEquals("X", todo.getStatusIcon());
    }
}
