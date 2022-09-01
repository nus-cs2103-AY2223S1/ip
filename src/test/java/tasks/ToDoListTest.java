package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;
import bobthebot.tasks.Todo;
import bobthebot.utils.Storage;

public class ToDoListTest {
    @Test
    public void markDoneTest() {
        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage("./../../data/data.txt");
        Task todo = new Todo("task1");
        ToDoList taskList = new ToDoList(tasks, storage);
        todo.markDone();
        assertEquals(todo.getStatusIcon(), "X");
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
