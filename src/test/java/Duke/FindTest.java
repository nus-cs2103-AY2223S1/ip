package Duke;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {

    @Test
    public void findTest1 () throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("return book"));
        tasks.add(new Deadline("read book ", "2022-08-08"));
        tasks.add(new Event("read book ", "2022-10-10"));
        TaskList tasks1 = new TaskList(tasks);
        String expected = " Here are the matching tasks in your list:\n" +
                " 1: [T][ ] return book\n" +
                " 2: [D][ ] read book (by: 08/08/2022)\n" +
                " 3: [E][ ] read book (at: 2022-10-10)";
        assertEquals(expected, tasks1.find("book"));
    }

    @Test
    public void findTest2 () throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("buy bread"));
        tasks.add(new Event("buy bread ", "2022-11-11"));
        TaskList tasks2 = new TaskList(tasks);
        String expected = " Here are the matching tasks in your list:\n" +
                " 1: [T][ ] buy bread\n" +
                " 2: [E][ ] buy bread (at: 2022-11-11)";
        assertEquals(expected, tasks2.find("bread"));
    }

    @Test
    public void findTest3 () throws WagwanException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("buy bread"));
        tasks.add(new Event("read book ", "2022-11-11"));
        TaskList tasks3 = new TaskList(tasks);
        String expected = " Here are the matching tasks in your list:\n" +
                " 1: [T][ ] buy bread";
        assertEquals(expected, tasks3.find("buy"));
    }
}
