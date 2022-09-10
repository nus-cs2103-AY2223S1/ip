package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.task.Priority;
import duke.task.TaskType;
import duke.task.ToDo;



public class ToDoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][ ][L] read book", new ToDo("read book", TaskType.TODO).toString());
    }

    @Test
    public void testStringConversionWithPriority() {
        assertEquals("[T][ ][H] read book", new ToDo("read book",
                TaskType.TODO, Priority.HIGH).toString());
    }

    @Test
    public void testChangePrioritySuccess() {
        ToDo todo = new ToDo("read book", TaskType.TODO, Priority.HIGH);
        todo.setPriority(Priority.MEDIUM);
        assertEquals(Priority.MEDIUM, todo.getPriority());
    }

    @Test
    public void testFindDescriptionSuccess() {
        ToDo todo = new ToDo("read book", TaskType.TODO, Priority.HIGH);
        boolean isPresent = todo.isQueriesPresent("book", "lab");
        assertTrue(isPresent);
    }

    @Test
    public void testCannotFindDescriptionSuccess() {
        ToDo todo = new ToDo("read book", TaskType.TODO, Priority.HIGH);
        boolean isPresent = todo.isQueriesPresent("meeting", "lab");
        assertFalse(isPresent);
    }

    @Test
    public void isDateEqual_success() {
        assertFalse(new ToDo("read book", TaskType.TODO).isDateEqual(LocalDate.now()));
    }
}
