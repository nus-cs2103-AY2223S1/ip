package seedu.duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void addTask_DeadlineObject_toStringSuccess () {
        TaskList tasks = new TaskList();

        Deadline deadline = new Deadline("return book", "2019-10-15");
        tasks.addTask(deadline);

        Task task = tasks.getTask(1);
        assertEquals(task, deadline);
    }
}