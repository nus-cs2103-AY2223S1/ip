package duke.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.data.exceptions.InvalidTaskException;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;

public class TaskListTest {
    @Test
    public void addTask_oneTask_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("test");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void getNumberOfTasks_twoTasks_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake");
        taskList.addTask(todo);
        Deadline deadline = new Deadline("read", "2022-09-14");
        taskList.addTask(deadline);
        assertEquals(2, taskList.getNumberOfTasks());
    }

    @Test
    public void changeTaskStatus_indexWithinBounds_success() throws InvalidTaskException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake");
        taskList.addTask(todo);
        taskList.changeTaskStatus(0, true);
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void changeTaskStatus_indexOutOfBounds_throwsInvalidTaskException() throws InvalidTaskException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake");
        taskList.addTask(todo);
        assertThrows(InvalidTaskException.class, () -> taskList.changeTaskStatus(1, true));
    }

    @Test
    public void deleteTask_indexWithinBounds_success() throws InvalidTaskException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake");
        taskList.addTask(todo);
        Deadline deadline = new Deadline("read", "2022-09-14");
        taskList.addTask(deadline);
        taskList.deleteTask(1);
        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void deleteTask_negativeIndex_throwsInvalidTaskException() throws InvalidTaskException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake");
        taskList.addTask(todo);
        Deadline deadline = new Deadline("read", "2022-09-14");
        taskList.addTask(deadline);
        assertThrows(InvalidTaskException.class, () -> taskList.deleteTask(-1));
    }

    @Test
    public void getTasksOnDate_oneTaskSameDate_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake");
        taskList.addTask(todo);
        Deadline deadline = new Deadline("read", "2022-09-14");
        taskList.addTask(deadline);
        assertEquals(deadline, taskList.getTasksOnDate("2022-09-14").get(0));
    }

    @Test
    public void getTasksOnDate_noTaskSameDate_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake");
        taskList.addTask(todo);
        Deadline deadline = new Deadline("read", "2022-09-14");
        taskList.addTask(deadline);
        assertTrue(taskList.getTasksOnDate("2022-03-13").isEmpty());
    }

    @Test
    public void getTasksWithKeywords_oneTaskWithKeyword_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake muffins");
        taskList.addTask(todo);
        assertEquals(todo, taskList.getTasksWithKeywords("muffins").get(0));
    }

    @Test
    public void getTasksWithKeywords_noTaskWithKeyword_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake muffins");
        taskList.addTask(todo);
        assertTrue(taskList.getTasksWithKeywords("book").isEmpty());
    }

    @Test
    public void changeTasks_dummyTaskList_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("bake muffins");
        taskList.addTask(todo);
        Todo newTodo = new Todo("dance");
        ArrayList<Task> newTaskList = new ArrayList<>();
        newTaskList.add(newTodo);
        taskList.changeTasks(newTaskList);
        assertEquals(newTodo, taskList.getTask(0));
    }
}
