package skylark.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_description_success() {
        ToDo toDoTask = new ToDo("Make tea");
        assertEquals("[T] [ ] Make tea", toDoTask.toString());
    }

    @Test
    public void toStringFile_description_success() {
        ToDo toDoTask = new ToDo("Make tea");
        assertEquals("T | 0 | Make tea", toDoTask.toStringFile());
        toDoTask.markAsDone();
        assertEquals("T | 1 | Make tea", toDoTask.toStringFile());
        toDoTask.markAsUndone();
        assertEquals("T | 0 | Make tea", toDoTask.toStringFile());
    }

    @Test
    public void getStatusIcon_description_success() {
        ToDo toDoTask = new ToDo("Make tea");
        assertEquals(" ", toDoTask.getStatusIcon());
        toDoTask.markAsDone();
        assertEquals("X", toDoTask.getStatusIcon());
        toDoTask.markAsUndone();
        assertEquals(" ", toDoTask.getStatusIcon());
    }

    @Test
    public void getDescription_description_success() {
        ToDo toDoTask = new ToDo("Make tea");
        assertEquals("Make tea", toDoTask.getDescription());
        ToDo toDoTask2 = new ToDo("Make coffee");
        assertEquals("Make coffee", toDoTask2.getDescription());
        ToDo toDoTask3 = new ToDo("Make cereal");
        assertEquals("Make cereal", toDoTask3.getDescription());
    }
}
