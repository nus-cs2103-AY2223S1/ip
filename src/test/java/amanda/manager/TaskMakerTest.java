package amanda.manager;

import main.java.amanda.exception.AmandaException;
import main.java.amanda.manager.TaskMaker;
import main.java.amanda.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskMakerTest {

    @Test
    void make() {
        TaskMaker tm = new TaskMaker();
        try {
            assertEquals(new Todo(" hi").getTask(), tm.make("todo hi").getTask());
        } catch (AmandaException e) {
            e.printStackTrace();
        }
    }
}