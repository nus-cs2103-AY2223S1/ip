package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void storageTest1() throws DukeException {
        Storage storage1 = new Storage("taskTest1.txt");
        TaskList tasks1 = new TaskList(storage1.load());
        String expected = " Here are the tasks in your list:\n" +
                " 1: [T][ ] sampletodo1\n" +
                " 2: [T][X] sampletodo2";
        assertEquals(expected, tasks1.toString());
    }

    @Test
    public void storageTest2() throws DukeException {
        Storage storage2 = new Storage("taskTest2.txt");
        TaskList tasks2 = new TaskList(storage2.load());
        String expected = " Here are the tasks in your list:\n" +
                " 1: [D][ ] sampledeadline1 (by: 08/08/2022)\n" +
                " 2: [D][X] sampledeadline2 (by: 09/09/2022)\n" +
                " 3: [E][ ] sampleevent1 (at: 2022-10-10)\n" +
                " 4: [E][X] sampleevent2 (at: 2022-11-11)";
        assertEquals(expected, tasks2.toString());
    }

    @Test
    public void storageTest3() throws DukeException {
        Storage storage3 = new Storage("taskTest3.txt");
        TaskList tasks3 = new TaskList(storage3.load());
        String expected = " Here are the tasks in your list:\n" +
                " 1: [T][ ] sampletodo1\n" +
                " 2: [T][X] sampletodo2\n" +
                " 3: [D][ ] sampledeadline1 (by: 08/08/2022)\n" +
                " 4: [D][X] sampledeadline2 (by: 09/09/2022)\n" +
                " 5: [E][ ] sampleevent1 (at: 2022-10-10)\n" +
                " 6: [E][X] sampleevent2 (at: 2022-11-11)";
        assertEquals(expected, tasks3.toString());
    }
}
