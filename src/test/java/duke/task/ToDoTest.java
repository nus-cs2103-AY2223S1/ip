package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.constant.PriorityLevel;

public class ToDoTest {
    @Test
    public void createToDo_noMarkSpecified_unmarkedToDoReturned() {
        ToDo todo = new ToDo("Test");
        assertEquals("[T][ ] Test", todo.toString());
    }

    @Test
    public void createToDo_unmarkedToDo_unmarkedToDoReturned() {
        ToDo todo = new ToDo("Test", false, PriorityLevel.NONE);
        assertEquals("[T][ ] Test", todo.toString());
    }

    @Test
    public void createToDo_markedToDo_markedToDoReturned() {
        ToDo todo = new ToDo("Test", true, PriorityLevel.NONE);
        assertEquals("[T][X] Test", todo.toString());
    }

    @Test
    public void toSaveFileString_unmarkedToDo_stringRepresentationMatch() {
        ToDo todo = new ToDo("Test", false, PriorityLevel.NONE);
        assertEquals("[T] @ [ ] @ none @ Test", todo.toSaveFileString());
    }

    @Test
    public void toSaveFileStringTest_markedToDo_stringRepresentationMatch() {
        ToDo todo = new ToDo("Test", true, PriorityLevel.NONE);
        assertEquals("[T] @ [X] @ none @ Test", todo.toSaveFileString());
    }

    @Test
    public void markAsDoneTest() {
        ToDo todo = new ToDo("Test", false, PriorityLevel.NONE);
        todo.markAsDone();
        assertEquals("[T][X] Test", todo.toString());
    }

    @Test
    public void markAsUndoneTest() {
        ToDo todo = new ToDo("Test", true, PriorityLevel.NONE);
        todo.markAsUndone();
        assertEquals("[T][ ] Test", todo.toString());
    }
}
