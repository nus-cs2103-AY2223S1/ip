package duke.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.DukeException;
import duke.Message;
import duke.task.TaskList;

class EventCommandTest {

    @Test()
    void testExecute_failure() throws DukeException {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        Mockito.when(mockedTaskList.addTask(Mockito.any())).thenReturn(false);

        try {
            new EventCommand("name", "2020-01-01 03:00").execute(mockedTaskList);
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
            Message message = new EventCommand("name", "2020-01-01 03:00")
                    .execute(mockedTaskList);
            Assertions.assertEquals(message.getClass(), Message.class);
            Assertions.assertFalse(message.shouldExit);
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    void testEquals() {
        EventCommand x = new EventCommand("name", "2020-01-01 03:00");
        EventCommand y = new EventCommand("name", "2020-01-01 03:00");
        EventCommand z = new EventCommand("name", "2020-01-01 03:01");
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
        Assertions.assertFalse(x.equals(z) || z.equals(x));
        Assertions.assertNotEquals(1, x);
    }
}
