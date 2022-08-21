package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList sampleTaskList;

    @BeforeEach
    public void setUp() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new TaskStub());
        tasks.add(new TaskStub());
        sampleTaskList = new TaskList(tasks);
    }

    @Test
    public void addTask_getCorrectNumberOfTasks() {
        sampleTaskList.addTask(new TaskStub());
        assertEquals(sampleTaskList.getTaskListSize(), 3);
    }

    @Test
    public void removeTask_validInput_getCorrectNumberOfTasks() {
        sampleTaskList.removeTaskWithIndex(1);
        assertEquals(sampleTaskList.getTaskListSize(), 1);
    }

    @Test
    public void getStringRepresentation_emptyTaskList_getCorrectRepresentation() {
        TaskList emptyTaskList = new TaskList();
        assertEquals(emptyTaskList.toString(), "You have no tasks at the moment.");
    }

    @Test
    public void getStringRepresentation_nonEmptyTaskList_getCorrectRepresentation() {
        String taskListStringRepresentation = sampleTaskList.toString();
        assertTrue(taskListStringRepresentation.startsWith("Here are the tasks in your list\n"));
    }

    @Test
    public void getStorageRepresentation_getCorrectNumberOfRepresentation() {
        List<String> storageRepresentation = sampleTaskList.toStorageRepresentation();
        assertEquals(storageRepresentation.size(), 2);
    }

}
