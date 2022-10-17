package wagwan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskTest {

    // test 1 : Task.getStatusIcon (!isDone)
    @Test
    public void taskTest1() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks1 = new TaskList(tasks);
        String expected = " ";
        assertEquals(expected, tasks1.getTasks().get(0).getStatusIcon());
    }

    // test 2 : Task.getStatusIcon (isDone)
    @Test
    public void taskTest2() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks2 = new TaskList(tasks);
        tasks2.getTasks().get(0).markAsDone();
        String expected = "X";
        assertEquals(expected, tasks2.getTasks().get(0).getStatusIcon());
    }

    // test 3 : updateDescription(String description)
    @Test
    public void taskTest3() throws WagwanException {
        Todo todo = new Todo("placeholder");
        todo.updateDescription("new description");
        String expected = "[T][ ] new description";
        assertEquals(expected, todo.toString());
    }

}
