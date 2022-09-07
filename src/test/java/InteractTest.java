import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import dukepro.exceptions.DukeException;
import dukepro.handlers.Decoder;
import dukepro.handlers.Manager;
import dukepro.handlers.TaskFunction;
import dukepro.tasks.Task;
import dukepro.tasks.Todo;

public class InteractTest {
    @Test
    public void handleTodoTest() {
        Task testTask;
        try {
            testTask = Decoder.handleTasks("todo sleep");
            assertEquals(testTask.toString(), "[T][X] sleep");
        } catch (DukeException e) {
            System.out.println("failed");
        }
    }

    @Test
    public void handleDeadlineTest() {
        Task testTask;
        try {
            testTask = Decoder.handleTasks("deadline cs2103t /by 2022-10-20");
            String correctString = "[D][X] cs2103t  (by: Oct 20 2022)";
            assertEquals(testTask.toString(), correctString);
        } catch (DukeException e) {
            System.out.println("failed");
        }
    }

    //TaskManager tests
    @Test
    public void addDeleteTest() {
        Function<String, Task> decoder = str -> Decoder.parseFromFile(str);
        Manager<Task> tm = new Manager<Task>("data/test", "data/test/testTasklist", decoder, "tasks");
        tm.add(new Todo("say hello"));
        tm.add(new Task("write essay"));
        tm.delete(2);
        tm.add(new Task("whatever"));
        assertEquals(tm.numStored(), 3);
    }

    @Test
    public void checkMarkDone() {
        Function<String, Task> decoder = str -> Decoder.parseFromFile(str);
        Manager<Task> tm = new Manager<Task>("data/test", "data/test/testTasklist", decoder, "tasks");
        Task test = new Todo("test");
        tm.add(test);
        int taskNo = 1;
        Task doneTask = tm.operateOnList(arr -> TaskFunction.markAsDone(arr, taskNo));
        String correct = "[T][/] test";
        assertEquals(correct, doneTask.toString());
    }
}
