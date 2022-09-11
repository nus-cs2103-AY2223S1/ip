package skylark.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_description_success() {
        Todo toDoTask = new Todo("Make tea");
        assertEquals("[T] [ ] Make tea", toDoTask.toString());
    }

    @Test
    public void toStringFile_description_success() {
        Todo toDoTask = new Todo("Make tea");
        assertEquals("T | 0 | Make tea", toDoTask.toStringFile());
        toDoTask.markAsDone();
        assertEquals("T | 1 | Make tea", toDoTask.toStringFile());
        toDoTask.markAsUndone();
        assertEquals("T | 0 | Make tea", toDoTask.toStringFile());
    }

    @Test
    public void getStatusIcon_description_success() {
        Todo toDoTask = new Todo("Make tea");
        assertEquals(" ", toDoTask.getStatusIcon());
        toDoTask.markAsDone();
        assertEquals("X", toDoTask.getStatusIcon());
        toDoTask.markAsUndone();
        assertEquals(" ", toDoTask.getStatusIcon());
    }

    @Test
    public void getDescription_description_success() {
        Todo toDoTask = new Todo("Make tea");
        assertEquals("Make tea", toDoTask.getDescription());
        Todo toDoTask2 = new Todo("Make coffee");
        assertEquals("Make coffee", toDoTask2.getDescription());
        Todo toDoTask3 = new Todo("Make cereal");
        assertEquals("Make cereal", toDoTask3.getDescription());
    }
}
