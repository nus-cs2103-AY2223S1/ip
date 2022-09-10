package duke.commands;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.DukeException;
import duke.Message;
import duke.task.TaskList;

class DeadlineCommandTest {

    @Test()
    void testExecute_failure() throws DukeException {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        Mockito.when(mockedTaskList.addTask(Mockito.any())).thenReturn(false);
        try {
            new DeadlineCommand("name", LocalDateTime.of(2020, 1, 1, 3, 0))
                    .execute(mockedTaskList);
            Assertions.fail();
        } catch (DukeException e) {
            // expected
        }
    }

    @Test
    void testExecute_success() throws DukeException {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        Mockito.when(mockedTaskList.addTask(Mockito.any())).thenReturn(true);
        try {
            Message message = new DeadlineCommand("name", LocalDateTime.of(2020, 1, 1, 3, 0))
                    .execute(mockedTaskList);
            Assertions.assertEquals(message.getClass(), Message.class);
            Assertions.assertFalse(message.shouldExit);
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    void testEquals() {
        DeadlineCommand x = new DeadlineCommand("description", LocalDateTime.MAX);
        DeadlineCommand y = new DeadlineCommand("description", LocalDateTime.MAX);
        DeadlineCommand z = new DeadlineCommand("description", LocalDateTime.MIN);
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
        Assertions.assertFalse(x.equals(z) || z.equals(x));
        Assertions.assertNotEquals("description", x);
    }
}
