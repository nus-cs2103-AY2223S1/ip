package roger.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    @Test
    public void testGetLength_passedInTasks_returnCorrectLength() {
        Task task1 = new TaskStub("task1");
        Task task2 = new TaskStub("task2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        TaskList taskList = new TaskList(tasks);

        assertEquals(taskList.getLength(), 2);
    }

    @Test
    public void testConstructor_passedInEmptyList_returnZero() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals(taskList.getLength(), 0);
    }

    @Test
    public void testGet() {
        Task task1 = new TaskStub("task1");
        Task task2 = new TaskStub("task2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        TaskList taskList = new TaskList(tasks);

        assertEquals(taskList.get(1), task1);
        assertEquals(taskList.get(2), task2);
    }

    @Test
    public void testFilter() {
        Task task1 = new TaskStub("task1");
        Task task2 = new TaskStub("task2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        TaskList taskList = new TaskList(tasks);

        assertEquals(taskList.filter(LocalDate.parse("2022-10-22")).size(), 0);
        assertEquals(taskList.filter(LocalDate.parse("2022-02-02")).size(), 2);
    }
}
