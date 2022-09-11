package Command;
import Duck.Storage;
import Duck.TaskList;
import Models.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void executeTest() throws IOException {
        List<Todo> list = new ArrayList<>(); //fake list from file
        TaskList<Todo> taskList = new TaskList<>(list);
        Todo t = new Todo("title", false);
        Commands c = new AddCommand(t);
        c.execute(taskList, new Storage("data", "duck.txt"));
        assertEquals(1, taskList.size());
    }
}
