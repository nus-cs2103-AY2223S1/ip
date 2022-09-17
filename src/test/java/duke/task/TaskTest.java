package duke.task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void markAsCompleted_incompleteTask_isCompletedReturnsTrue(){
        Task task = new Task("Test task", false);
        task.markAsCompleted();
        assertEquals(task.isCompleted(), true);
    }

    @Test
    public void markAsCompleted_completeTask_isCompletedReturnsTrue(){
        Task task = new Task("Test task", true);
        task.markAsCompleted();
        assertEquals(task.isCompleted(), true);
    }

    @Test
    public void markAsIncomplete_incompleteTask_isCompletedReturnsFalse(){
        Task task = new Task("Test task", false);
        task.markAsIncomplete();
        assertEquals(task.isCompleted(), false);
    }

    @Test
    public void markAsInomplete_completeTask_isCompletedReturnsFalse(){
        Task task = new Task("Test task", true);
        task.markAsIncomplete();
        assertEquals(task.isCompleted(), false);
    }
}
