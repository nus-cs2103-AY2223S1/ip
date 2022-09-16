package duke;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testTaskList() throws DukeException {

        ToDo todo1 = new ToDo("HW1");
        ToDo todo2 = new ToDo("HW2");
        assertEquals("T | false | HW1\n", todo1.save());
        TaskList tasks = new TaskList();
        tasks.addTask(todo1);
        tasks.addTask(todo2);
        tasks.markAsDone(0);
        assertEquals("T | true | HW1\n", tasks.getTaskByIndex(0).save());

    }

}