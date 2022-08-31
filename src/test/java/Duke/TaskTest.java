package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    // test 1 : Task.getStatusIcon (!isDone)
    @Test
    public void taskTest1() throws DukeException {
        Storage storage1 = new Storage("taskTest1.txt");
        TaskList tasks1 = new TaskList(storage1.load());
        String expected = " ";
        assertEquals(expected, tasks1.getTasks().get(0).getStatusIcon());
    }

    // test 2 : Task.getStatusIcon (isDone)
    @Test
    public void taskTest2() throws DukeException {
        Storage storage2 = new Storage("taskTest1.txt");
        TaskList tasks2 = new TaskList(storage2.load());
        tasks2.getTasks().get(0).markAsDone();
        String expected = "X";
        assertEquals(expected, tasks2.getTasks().get(1).getStatusIcon());
    }

    // test 3 : Task.markAsDone
    @Test
    public void taskTest3() throws DukeException {
        Storage storage3 = new Storage("taskTest1.txt");
        TaskList tasks3 = new TaskList(storage3.load());
        tasks3.getTasks().get(0).markAsDone();
        String expected = " Here are the tasks in your list:\n" +
                " 1: [T][X] sampletodo1\n" +
                " 2: [T][X] sampletodo2";
        assertEquals(expected, tasks3.toString());
    }

    // test 3 : Task.markAsUndone
    @Test
    public void taskTest4() throws DukeException {
        Storage storage4 = new Storage("taskTest1.txt");
        TaskList tasks4 = new TaskList(storage4.load());
        tasks4.getTasks().get(1).markAsUndone();
        String expected = " Here are the tasks in your list:\n" +
                " 1: [T][ ] sampletodo1\n" +
                " 2: [T][ ] sampletodo2";
        assertEquals(expected, tasks4.toString());
    }
}
