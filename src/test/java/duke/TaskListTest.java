package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_addTodoTask_success() {
        TaskList tl = new TaskList();
        Task t = new Todo("read books");
        tl.addTask((t));
        assertEquals(t, tl.getTasks().get(0));
    }

    @Test
    public void deleteTask_deleteTodoTask_success() {
        ArrayList<Task> al = new ArrayList<>();
        Task todo = new Todo("return books");
        Task event = new Event("attend wedding", "2022-10-28 18:00");
        al.add(todo);
        al.add(event);

        TaskList tl = new TaskList(al);
        tl.deleteTask(0);
        assertEquals(event, tl.getTasks().get(0));
    }

}
