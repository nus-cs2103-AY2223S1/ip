package duke.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.task.TaskList;

class ExitCommandTest {

    @Test
    void testExecute() {
        Assertions.assertTrue(new ExitCommand().execute(Mockito.mock(TaskList.class)).shouldExit);
    }

    @Test
    void testEquals() {
        ExitCommand x = new ExitCommand();
        ExitCommand y = new ExitCommand();
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
    }
}
