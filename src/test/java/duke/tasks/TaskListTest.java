package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;



public class TaskListTest {

    private final TaskList taskList = new TaskList(new ArrayList<>() {
        {
            add(new ToDoTask("read book"));
            add(new EventTask("meeting", LocalDate.parse("2022-08-23")));
            add(new DeadlineTask("finish assignment", LocalDate.parse("2022-09-15")));
        }
    });

    @Test
    public void sizeTest() {
        assertEquals(3, taskList.size());
    }

    @Test
    public void getTest() {
        assertEquals(new ToDoTask("read book").toString(), taskList.get(0).toString());
    }
}
