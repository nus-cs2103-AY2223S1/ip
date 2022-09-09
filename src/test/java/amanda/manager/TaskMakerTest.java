package amanda.manager;

import amanda.exception.AmandaException;
import amanda.manager.TaskMaker;
import amanda.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskMakerTest {

    @Test
    void make() {
        TaskMaker tm = new TaskMaker();
        try {
            assertEquals(new Todo(" hi").getDesc(), tm.make("todo hi").getDesc());
        } catch (AmandaException e) {
            e.printStackTrace();
        }
    }
}