import dukepro.exceptions.DukeException;
import dukepro.handlers.Decoder;
import dukepro.handlers.TasksManager;
import dukepro.tasks.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InteractTest {
    //Decoder Tests
    @Test
    public void handleTodoTest(){
        Task testTask;
        try {
            testTask = Decoder.handleTasks("todo sleep");
            assertEquals(testTask.toString(), "[T][X] sleep");
        } catch (DukeException e) {
            System.out.println("failed");
        }
    }

    @Test
    public void handleDeadlineTest(){
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
        TasksManager tm = new TasksManager();
        tm.addTask(new Todo("say hello"));
        tm.addTask(new Task("write essay"));
        tm.deleteTask(2);
        tm.addTask(new Task("whatever"));
        assertEquals(tm.numTasks(), 3);
    }

    @Test
    public void checkMarkDone() {
        TasksManager tm = new TasksManager();
        Task test = new Todo("test");
        tm.addTask(test);
        tm.markTaskAsDone(1);
        String correct = "[T][/] test";
        assertEquals(correct, test.toString());
    }
}
