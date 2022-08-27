package tasks;

import tasks.Task;
import tasks.Todo;
import tasks.ToDoList;
import utils.Storage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoListTest {
    @Test
    public void markDoneTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage("./../../data/data.txt");
        Task todo = new Todo("task1");
        ToDoList taskList = new ToDoList(tasks, storage);
        todo.markDone();
        assertEquals(todo.getStatusIcon(), "✔");
    }

    @Test
    public void markUndoneTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage("./../../data/data.txt");
        Task todo = new Todo("task2");
        ToDoList taskList = new ToDoList(tasks, storage);
        todo.markDone();
        todo.markUndone();
        assertEquals(todo.getStatusIcon(), " ");
    }
}
