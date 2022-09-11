package skylark.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void toString_description_success() {
        Todo toDoTask = new Todo("Make tea");
        assertEquals("[T] [ ] Make tea", toDoTask.toString());
        toDoTask.markAsDone();
        assertEquals("[T] [X] Make tea", toDoTask.toString());
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
    public void toString_descriptionWTag_success() {
        ToDo toDoTask = new ToDo("Make tea", "Test tag!");
        assertEquals("[T] [ ] Make tea TAG: Test tag!", toDoTask.toString());
        toDoTask.markAsDone();
        assertEquals("[T] [X] Make tea TAG: Test tag!", toDoTask.toString());
    }

    @Test
    public void toStringFile_descriptionWTag_success() {
        ToDo toDoTask = new ToDo("Make tea", "Test tag!");
        assertEquals("T | 0 | Make tea | Test tag!", toDoTask.toStringFile());
        toDoTask.markAsDone();
        assertEquals("T | 1 | Make tea | Test tag!", toDoTask.toStringFile());
        toDoTask.markAsUndone();
        assertEquals("T | 0 | Make tea | Test tag!", toDoTask.toStringFile());
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

    @Test
    public void setTag_tag_success() {
        ToDo toDoTask = new ToDo("Make tea", "Old tag!");
        assertEquals("[T] [ ] Make tea TAG: Old tag!", toDoTask.toString());
        toDoTask.setTag("New tag!");
        assertEquals("[T] [ ] Make tea TAG: New tag!", toDoTask.toString());
        toDoTask.setTag("New tag again!");
        assertEquals("[T] [ ] Make tea TAG: New tag again!", toDoTask.toString());
    }
}
