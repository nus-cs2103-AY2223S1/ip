package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {

    @Test
    public void findTest1 () throws DukeException {
        Storage storage1 = new Storage("taskTest4.txt");
        TaskList tasks1 = new TaskList(storage1.load());
        String expected = " Here are the matching tasks in your list:\n" +
                " 1: [T][ ] return book\n" +
                " 2: [D][ ] read book (by: 08/08/2022)\n" +
                " 3: [E][ ] read book (at: 2022-10-10)";
        assertEquals(expected, tasks1.find("book"));
    }

    @Test
    public void findTest2 () throws DukeException {
        Storage storage2 = new Storage("taskTest4.txt");
        TaskList tasks2 = new TaskList(storage2.load());
        String expected = " Here are the matching tasks in your list:\n" +
                " 1: [T][X] buy bread\n" +
                " 2: [E][X] bread (at: 2022-11-11)";
        assertEquals(expected, tasks2.find("bread"));
    }

    @Test
    public void findTest3 () throws DukeException {
        Storage storage3 = new Storage("taskTest4.txt");
        TaskList tasks3 = new TaskList(storage3.load());
        String expected = " Here are the matching tasks in your list:\n" +
                " 1: [T][ ] return book\n" +
                " 2: [T][X] buy bread\n" +
                " 3: [D][ ] read book (by: 08/08/2022)\n" +
                " 4: [D][X] test (by: 09/09/2022)\n" +
                " 5: [E][ ] read book (at: 2022-10-10)\n" +
                " 6: [E][X] bread (at: 2022-11-11)";
        assertEquals(expected, tasks3.find("e"));
    }

    @Test
    public void findTest4 () throws DukeException {
        Storage storage4 = new Storage("taskTest4.txt");
        TaskList tasks4 = new TaskList(storage4.load());
        String expected = " Here are the matching tasks in your list:\n" +
                " 1: [T][X] buy bread";
        assertEquals(expected, tasks4.find("buy"));
    }
}
