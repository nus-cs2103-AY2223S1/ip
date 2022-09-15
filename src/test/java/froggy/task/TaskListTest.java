package seedu.duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    public void addTask_deadlineObject_toStringSuccess() {
        TaskList tasks = new TaskList();

        Deadline deadline = new Deadline("return book", "2019-10-15");
        tasks.addTask(deadline);

        Task task = tasks.getTask(1);
        assertEquals(task, deadline);
    }
}