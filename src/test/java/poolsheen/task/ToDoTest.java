package poolsheen.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
    public void getStatusIcon_toDoDone_success() {
        ToDo td = new ToDo("desc", true);
        assertEquals("X", td.getStatusIcon());
    }

    @Test
    public void getStatusIcon_toDoNotDone_success() {
        ToDo td = new ToDo("desc", false);
        assertEquals("-", td.getStatusIcon());
    }

    @Test
    public void setDesc_zeroSpaceDesc_success() {
        ToDo td = new ToDo("desc", false);
        td.setDesc("newDesc");
        assertEquals(td.getDescription(), "newDesc");
    }

    @Test
    public void setDesc_singleSpaceDesc_success() {
        ToDo td = new ToDo("desc", false);
        td.setDesc("new Desc");
        assertEquals(td.getDescription(), "new Desc");
    }

    @Test
    public void setDesc_doubleSpaceDesc_success() {
        ToDo td = new ToDo("desc", false);
        td.setDesc("new Desc Desc");
        assertEquals(td.getDescription(), "new Desc Desc");
    }
}
