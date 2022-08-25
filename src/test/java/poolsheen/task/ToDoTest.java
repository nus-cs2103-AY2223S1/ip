package poolsheen.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ToDoTest {

    @Test
    public void toArr_properToDo_success() {
        String[] tdArr = new ToDo("desc", true).toArr();
        String[] expected = new String[]{"T", "X", "desc"};
        assertArrayEquals(expected, tdArr);
    }

    @Test
    public void markAsDone_properToDo_success() {
        ToDo td = new ToDo("desc", false);
        td.markAsDone();
        assertEquals(true, td.isDone);
    }

    @Test
    public void markAsNotDone_properToDo_success() {
        ToDo td = new ToDo("desc", true);
        td.markAsNotDone();
        assertEquals(false, td.isDone);
    }

    @Test
    public void getStatusIcon_ToDoDone_success() {
        ToDo td = new ToDo("desc", true);
        assertEquals("X", td.getStatusIcon());
    }

    @Test
    public void getStatusIcon_ToDoNotDone_success() {
        ToDo td = new ToDo("desc", false);
        assertEquals("-", td.getStatusIcon());
    }
}
