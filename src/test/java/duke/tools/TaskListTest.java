package duke.tools;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Todo;

class TaskListTest {

    private static class DummyTask extends Task {
        public DummyTask() {
            super("dummy", Task.TaskType.TODO);
        }
    }

    @Test
    void getStoredTasksTest() {
        List<Task> storedTasks = new ArrayList<>();
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        TaskList taskList = new TaskList(storedTasks);
        assertEquals(storedTasks, taskList.getStoredTasks());
    }

    @Test
    void getSize() {
        List<Task> storedTasks = new ArrayList<>();
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        storedTasks.add(new DummyTask());
        TaskList taskList = new TaskList(storedTasks);
        assertEquals(3, taskList.getSize());
    }

    @Test
    void getTask() {
        Task one = new Todo("One");
        Task two = new Todo("Two");
        Task three = new Todo("Three");
        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);
        TaskList taskList = new TaskList(arrayList);
        try {
            assertEquals(two, taskList.getTask(1));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void setTaskDoneStatus() {
        Task one = new Todo("One");
        Task two = new Todo("Two");
        Task three = new Todo("Three");
        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);
        TaskList taskList = new TaskList(arrayList);
        try {
            taskList.setTaskDoneStatus(1, true);
        } catch (DukeException e) {
            fail();
        }
        assertEquals(true, two.isDone());
    }

    @Test
    void addTask() {
        Task one = new Todo("One");
        ArrayList<Task> arrayList = new ArrayList<>();
        TaskList taskList = new TaskList(arrayList);
        taskList.addTask(one);
        assertEquals(one, arrayList.get(0));
    }

    @Test
    void deleteTask() {
        Task one = new Todo("One");
        Task two = new Todo("Two");
        Task three = new Todo("Three");
        ArrayList<Task> arrayList = new ArrayList<>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);
        TaskList taskList = new TaskList(arrayList);
        try {
            taskList.deleteTask(1);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(one, arrayList.get(0));
        assertEquals(three, arrayList.get(1));
        assertEquals(2, arrayList.size());
    }
}
