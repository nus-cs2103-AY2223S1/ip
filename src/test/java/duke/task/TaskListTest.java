package duke.task;

import duke.Parser;
import duke.command.Command;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    @Test
    public void markAsDone_invalidTaskNumber_exceptionThrown() {
        try {
            String path = "./src/test/data/duke.txt";
            TasksList tasksList = new TasksList(path);
            tasksList.markAsDone(-1);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid task number!", e.getMessage());
        }
    }

    @Test
    public void markAsUndone_invalidTaskNumber_exceptionThrown() {
        try {
            String path = "./src/test/data/duke.txt";
            TasksList tasksList = new TasksList(path);
            tasksList.markAsUndone(-1);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid task number!", e.getMessage());
        }
    }

    @Test
    public void parseToTasks_nullString_EmptyArrayList() {
        String path = "./src/test/data/duke.txt";
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        TasksList taskList = new TasksList(path);
        assertEquals(0, taskList.getLength());
    }



}
