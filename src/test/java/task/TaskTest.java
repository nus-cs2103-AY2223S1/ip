package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void getStatusIcon_newTaskWithDescription_success(){
        assertEquals(" ", new Task("test description").getStatusIcon());
    }

    @Test
    public void getStatusIcon_newDoneTaskWithDescription_success(){
        assertEquals("X", new Task("X", "test description").getStatusIcon());
    }

    @Test
    public void markAsDone_getStatusIconEqualsX_success(){
        Task testTask = new Task("test description");
        testTask.markAsDone();
        assertEquals("X", testTask.getStatusIcon());
    }

    @Test
    public void markAsUndone_getStatusIconEqualsSpace_success(){
        Task testTask = new Task("X", "test description");
        testTask.markAsUndone();
        assertEquals(" ", testTask.getStatusIcon());
    }
}