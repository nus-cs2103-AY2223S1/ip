package tuna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import tuna.task.Task;
import tuna.utility.TaskList;

/**
 * Tests to test the TaskList class.
 */
public class TaskListTest {

    /**
     * Tests if the getTask function works as intended, which should return the task at the specified index.
     */
    @Test
    public void getTask_twoTasksTotal_todoTaskReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        tasks.addTodo("second description");
        assertEquals("description", tasks.getTask(0).getDescription());
    }

    /**
     * Tests if the getTotalTasks function works as intended, which should return the total number of tasks
     * in the list.
     */
    @Test
    public void getTotalTasks_oneTaskTotal_oneReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        assertEquals(1, tasks.getTotalTasks());
    }

    /**
     * Tests if the getLatestTask function works as intended, which should return the most recent task that was added.
     */
    @Test
    public void getLatestTask_twoTasksTotal_secondTaskReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        tasks.addTodo("second description");
        assertEquals("second description", tasks.getLatestTask().getDescription());
    }

    /**
     * Tests if the deleteTask function works as intended, which should delete the task from the task list.
     */
    @Test
    public void deleteTask_twoTasksTotal_firstTaskDeleted() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        tasks.addTodo("second description");
        tasks.deleteTask(1);
        assertEquals(1, tasks.getTotalTasks());
        assertEquals("description", tasks.getTask(0).getDescription());
    }

    /**
     * Tests if the addTodo function works as intended, which should add a Todo task to the task list.
     */
    @Test
    public void addTodo_zeroTasksInitially_taskAddedToTaskList() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        assertEquals(1, tasks.getTotalTasks());
        assertEquals("T", tasks.getTask(0).getTaskType());
    }

    /**
     * Tests if the addDeadLine function works as intended, which should add a DeadLine task to the task list.
     *
     * @throws TunaException Exception thrown when date and time provided is not formatted correctly.
     */
    @Test
    public void addDeadLine_zeroTasksInitially_taskAddedToTaskList() throws TunaException {
        TaskList tasks = new TaskList();
        tasks.addDeadLine("description", "2022-08-22 12:00");
        assertEquals(1, tasks.getTotalTasks());
        assertEquals("D", tasks.getTask(0).getTaskType());
    }

    /**
     * Tests if the addEvent function works as intended, which should add an Event task to the task list.
     *
     * @throws TunaException Exception thrown when date and time provided is not formatted correctly.
     */
    @Test
    public void addEvent_zeroTasksInitially_taskAddedToTaskList() throws TunaException {
        TaskList tasks = new TaskList();
        tasks.addEvent("description", "2022-08-22 12:00");
        assertEquals(1, tasks.getTotalTasks());
        assertEquals("E", tasks.getTask(0).getTaskType());
    }

    /**
     * Tests if the listTasks function works as intended, which should return an ArrayList copy of the task list.
     */
    @Test
    public void listTasks_tasksList_copyOfTasksListReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        ArrayList<Task> newTaskList = tasks.listTasks();
        assertEquals(tasks.getTotalTasks(), newTaskList.size());
        assertEquals(tasks.getTask(0), newTaskList.get(0));
    }

    /**
     * Tests if the listTasks function works with a LocalDate condition as intended, which should return an ArrayList
     * of the tasks found.
     */
    @Test
    public void listTasksWithDate_dateOfFirstTask_tasksFoundReturned() throws TunaException {
        TaskList tasks = new TaskList();
        tasks.addEvent("event", "2022-08-22 12:00");
        tasks.addDeadLine("deadline", "2022-08-23 12:00");
        ArrayList<Task> newTaskList = tasks.listTasks(LocalDate.of(2022, 8, 22));
        assertEquals(1, newTaskList.size());
        assertEquals("E", newTaskList.get(0).getTaskType());
        assertEquals("event", newTaskList.get(0).getDescription());
    }

    /**
     * Tests if the markItem function works as intended, which should mark the task at the specified index as done.
     */
    @Test
    public void markItem_markFirstTask_taskMarkedAsDone() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        tasks.markItem(0);
        assertEquals("X", tasks.getTask(0).getStatusIcon());
    }

    /**
     * Tests if the unMarkItem function works as intended, which should un-mark the task at the specified index.
     */
    @Test
    public void unMarkItem_unMarkFirstTask_taskUnMarkedAsDone() {
        TaskList tasks = new TaskList();
        tasks.addTodo("description");
        tasks.markItem(0);
        tasks.unMarkItem(0);
        assertEquals(" ", tasks.getTask(0).getStatusIcon());
    }

    /**
     * Tests if the find function works as intended, which should return an ArrayList of the tasks found.
     */
    @Test
    public void find_wordFoundInFirstAndSecondTask_tasksFoundReturned() {
        TaskList tasks = new TaskList();
        tasks.addTodo("complete assignment");
        tasks.addTodo("complete tasks");
        tasks.addTodo("read book");
        ArrayList<Task> tasksFound = tasks.find("complete");
        assertEquals(2, tasksFound.size());
        assertEquals("complete assignment", tasksFound.get(0).getDescription());
        assertEquals("complete tasks", tasksFound.get(1).getDescription());
    }

    /**
     * Tests if the sort function works as intended, which should sort the tasks in chronological order.
     *
     * @throws TunaException Exception thrown if the date and time for time based tasks is not formatted correctly.
     */
    @Test
    public void sort_taskListWithReverseSortedTasks_tasksSortedChronologically() throws TunaException {
        TaskList tasks = new TaskList();
        tasks.addTodo("complete assignment");
        tasks.addDeadLine("complete tasks", "2022-08-23 12:00");
        tasks.addEvent("read book", "2022-08-22 12:00");
        tasks.sort();
        assertEquals("read book", tasks.getTask(0).getDescription());
        assertEquals("complete tasks", tasks.getTask(1).getDescription());
        assertEquals("complete assignment", tasks.getTask(2).getDescription());
    }
}
