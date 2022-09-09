package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void getTaskDescriptionTest() {
        String actual = new TodoTask("CS2103").getTaskDescription();
        String expected = "CS2103";
        assertEquals(expected, actual);
    }

    @Test
    public void markCompleteTest() {
        Task task = new TodoTask("CS2103");
        task.markComplete();
        boolean actual = task.isCompleted();
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    public void markIncompleteTest() {
        Task task = new TodoTask("CS2103");
        task.markComplete();
        task.markIncomplete();
        boolean actual = task.isCompleted();
        boolean expected = false;
        assertEquals(expected, actual);
    }

}
