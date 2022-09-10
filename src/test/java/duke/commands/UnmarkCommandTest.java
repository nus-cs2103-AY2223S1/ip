package duke.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

class UnmarkCommandTest {

    @Test()
    void testExecute() throws DukeException {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        Mockito.when(mockedTaskList.setCompletion(Mockito.anyInt(), Mockito.anyBoolean()))
                .thenReturn(new ToDo("x"));
        new UnmarkCommand(0).execute(mockedTaskList);
    }

    @Test
    void testEquals() {
        UnmarkCommand x = new UnmarkCommand(1);
        UnmarkCommand y = new UnmarkCommand(1);
        UnmarkCommand z = new UnmarkCommand(-1);
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
        Assertions.assertFalse(x.equals(z) || z.equals(x));
        Assertions.assertNotEquals(1, x);
    }
}
