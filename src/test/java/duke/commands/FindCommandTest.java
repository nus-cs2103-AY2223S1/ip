package duke.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import duke.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

class FindCommandTest {

    @Test()
    void testExecute_failure() {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        Mockito.when(mockedTaskList.filterByKeyword(Mockito.any())).thenReturn(new TaskList());

        try {
            new FindCommand("name").execute(mockedTaskList);
            Assertions.fail();
        } catch (DukeException e) {
            // expected
        }
    }

    @Test
    void testExecute_success() throws DukeException {
        TaskList mockedTaskList = Mockito.mock(TaskList.class);
        TaskList filteredTaskList = new TaskList();
        filteredTaskList.addTask(new ToDo("name"));
        Mockito.when(mockedTaskList.filterByKeyword(Mockito.any())).thenReturn(filteredTaskList);
        try {
            new FindCommand("name").execute(mockedTaskList);
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    void testEquals() {
        FindCommand x = new FindCommand("a");
        FindCommand y = new FindCommand("a");
        FindCommand z = new FindCommand("b");
        Assertions.assertTrue(x.equals(y) && y.equals(x));
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x.hashCode(), y.hashCode());
        Assertions.assertFalse(x.equals(z) || z.equals(x));
        Assertions.assertNotEquals(1, x);
    }
}
