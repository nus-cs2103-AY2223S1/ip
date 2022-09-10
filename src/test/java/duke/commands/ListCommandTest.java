package duke.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.task.TaskList;

class ListCommandTest {

    @Test()
    void testExecute() {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        new ListCommand().execute(mockedTaskList);
    }

    @Test
    void testEquals() {
        ListCommand x = new ListCommand();
        ListCommand y = new ListCommand();
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
    }
}
