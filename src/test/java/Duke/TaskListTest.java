package Duke;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    // test 1 : getTaskListSize()
    @Test
    public void taskListTest1() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks1 = new TaskList(tasks);
        int expected = 3;
        assertEquals(expected, tasks1.getTaskListSize());
    }

    // test 2 : addTask(Task task)
    @Test
    public void taskListTest2() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks2 = new TaskList(tasks);
        tasks2.addTask(new Todo("new task"));
        int expected = 4;
        assertEquals(expected, tasks2.getTaskListSize());
    }

    // test 3 : deleteTask(int index)
    @Test
    public void taskListTest3() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks3 = new TaskList(tasks);
        tasks3.deleteTask(1);
        int expected = 2;
        assertEquals(expected, tasks3.getTaskListSize());
    }

    // test 4 : updateTask(int index, String newDescription)
    @Test
    public void taskListTest4() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks4 = new TaskList(tasks);
        tasks4.updateTask(1, "new description");
        String expected = "[T][ ] new description";
        assertEquals(expected, tasks4.getTasks().get(0).toString());
    }

    // test 5 : markTaskAsDone(int index)
    @Test
    public void taskListTest5() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks5 = new TaskList(tasks);
        tasks5.markTaskAsDone(1);
        String expected = "[T][X] return book";
        assertEquals(expected, tasks5.getTasks().get(0).toString());
    }

    // test 6 : markTaskAsDone(int index)
    @Test
    public void taskListTest6() throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks6 = new TaskList(tasks);
        tasks6.markTaskAsDone(1);
        tasks6.markTaskAsUndone(1);
        String expected = "[T][ ] return book";
        assertEquals(expected, tasks6.getTasks().get(0).toString());
    }



}
