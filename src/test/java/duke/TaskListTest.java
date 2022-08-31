package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

public class TaskListTest {
    private Task task1 = new Todo(TaskType.TODO, "todo", false);
    private Task task2 = new Deadline(TaskType.DEADLINE, "deadline", false, "1200, 12/12/2022");
    private Task task3 = new Event(TaskType.EVENT, "event", false, "1200, 12/12/2022");

    @Test
    public void checkEmptyTaskList_emptyConstructor_returnEmptyTaskList() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
    }

    @Test
    public void checkArrayLength_arrayList_returnArrayLength() {
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(task1);
        arr.add(task2);
        arr.add(task3);
        TaskList taskList = new TaskList(arr);
        assertEquals(taskList.getSize(), 3);
    }

    @Test
    public void checkLastTask_arrayList_returnLastTask() {
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(task1);
        arr.add(task2);
        arr.add(task3);
        TaskList taskList = new TaskList(arr);
        assertEquals(taskList.getTask(taskList.getSize() - 1).toString(),
                "[E][ ] event (at: 12:00 PM, Mon, 12 Dec 2022)");
    }

    @Test
    public void checkLastTaskRemoved_arrayList_returnNewLastTask() {
        ArrayList<Task> arr = new ArrayList<Task>();
        arr.add(task1);
        arr.add(task2);
        arr.add(task3);
        TaskList taskList = new TaskList(arr);
        taskList.removeTask(2);
        assertEquals(taskList.getTask(taskList.getSize() - 1).toString(),
                "[D][ ] deadline (by: 12:00 PM, Mon, 12 Dec 2022)");
    }
}
