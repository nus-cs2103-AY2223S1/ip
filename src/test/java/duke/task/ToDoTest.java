package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void toDoToStringTest() {
        ToDo td = new ToDo("iP Week 3", false);
        assertEquals(" [T][ ] iP Week 3", td.toString());
    }

    @Test
    public void markToDoTest() {
        ToDo td = new ToDo("iP Week 3", false);
        td.markAsDone();
        assertEquals(" [T][X] iP Week 3", td.toString());
    }

    @Test
    public void unmarkToDoTest() {
        ToDo td = new ToDo("iP Week 3", true);
        td.markAsUndone();
        assertEquals(" [T][ ] iP Week 3", td.toString());
    }

    @Test
    public void toDoSaveStringFormatTest1() {
        ToDo td = new ToDo("iP Week 3", false);
        assertEquals("T | 0 | iP Week 3", td.saveStringFormat());
    }

    @Test
    public void toDoSaveStringFormatTest2() {
        ToDo td = new ToDo("iP Week 3", true);
        assertEquals("T | 1 | iP Week 3", td.saveStringFormat());
    }

}
