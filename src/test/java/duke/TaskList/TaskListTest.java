package duke.TaskList;

import duke.Duke;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addToDoTest(){
        TaskList list = new TaskList();
        assertEquals(list.toString(), "");
        list.addTask(new ToDo("Return book"));
        assertEquals(list.toString(), "1.[T][✘] Return book\n");
    }

    @Test
    public void deleteTest() throws DukeException {
        TaskList list = new TaskList();
        assertEquals(list.toString(), "");
        list.addTask(new ToDo("Return book"));
        assertEquals(list.toString(), "1.[T][✘] Return book\n");
        list.delete("1");
        assertEquals(list.toString(), "");
    }
}
