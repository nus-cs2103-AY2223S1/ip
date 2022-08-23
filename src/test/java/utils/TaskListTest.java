package utils;

import entities.Task;
import entities.Todo;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
