package duke;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void taskListTest1(){
        new TaskList(new ArrayList<Task>());
        TaskList.addTaskToArray("deadline assignment /by 2019-04-22 18:30", Task.Type.DEADLINE);
        TaskList.addTaskToArray("todo work", Task.Type.TODO);
        TaskList.markAsDone("2");
        boolean test = TaskList.getTaskList().get(1).isDone;
        assertTrue(test);
    }

    @Test
    public void taskListTest2(){

        ArrayList<Task> t2 = new ArrayList<>();
        new TaskList(t2);
        TaskList.addTaskToArray("deadline assignment /by 2019-04-22 18:30", Task.Type.DEADLINE);
        TaskList.addTaskToArray("todo work", Task.Type.TODO);
        assertEquals(2, t2.size());
    }
}
