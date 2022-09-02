package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import entities.Task;
import entities.Todo;
import exceptions.DukeException;

public class TaskListTest {
    @Test
    public void testMarkTask() {
        List<Task> tasks = new ArrayList<>();
        Task todo = new Todo("todo");
        tasks.add(todo);
        todo.markAsUndone();
        try {
            TaskList t = new TaskList(tasks);
            t.markTaskAsDone(0);
            assertEquals(todo.getStatus(), true);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
