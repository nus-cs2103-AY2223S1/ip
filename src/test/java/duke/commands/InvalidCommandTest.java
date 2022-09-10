package duke.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.DukeException;
import duke.task.TaskList;

class InvalidCommandTest {

    @Test()
    void testExecute() {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        try {
            new InvalidCommand("message").execute(mockedTaskList);
            Assertions.fail();
        } catch (DukeException e) {
            // expected
        }
    }

    @Test
    void testEquals() {
        InvalidCommand x = new InvalidCommand("a");
        InvalidCommand y = new InvalidCommand("a");
        InvalidCommand z = new InvalidCommand("%s", "b");
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
        Assertions.assertFalse(x.equals(z) || z.equals(x));
        Assertions.assertNotEquals(1, x);
    }
}
