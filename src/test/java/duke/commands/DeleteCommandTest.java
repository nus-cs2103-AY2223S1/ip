package duke.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.DukeException;
import duke.Message;
import duke.task.TaskList;
import duke.task.ToDo;

class DeleteCommandTest {

    @Test()
    void testExecute_failure() throws DukeException {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        Mockito.when(mockedTaskList.removeTask(Mockito.anyInt())).thenThrow(new IndexOutOfBoundsException());

        try {
            new DeleteCommand(0).execute(mockedTaskList);
            Assertions.fail();
        } catch (DukeException e) {
            // expected
        }
    }

    @Test
    void testExecute_success() throws DukeException {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        Mockito.when(mockedTaskList.removeTask(Mockito.anyInt())).thenReturn(new ToDo("name"));
        try {
            Message message = new DeleteCommand(0)
                    .execute(mockedTaskList);
            Assertions.assertEquals(message.getClass(), Message.class);
            Assertions.assertFalse(message.shouldExit);
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    void testEquals() {
        DeleteCommand x = new DeleteCommand(1);
        DeleteCommand y = new DeleteCommand(1);
        DeleteCommand z = new DeleteCommand(-1);
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
        Assertions.assertFalse(x.equals(z) || z.equals(x));
        Assertions.assertNotEquals(1, x);
    }
}
