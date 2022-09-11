package Command;
import Duck.Storage;
import Duck.TaskList;
import Models.Todo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void executeTest() throws IOException {
        List<Todo> list = new ArrayList<>(); //fake list from file
        TaskList<Todo> taskList = new TaskList<>(list);
        Storage s = new Storage("data", "duck.txt");

        Todo t = new Todo("title", false);
        Commands c = new AddCommand(t);
        c.execute(taskList, s);
        assertEquals(1, taskList.size());

        c = new DeleteCommand(0);
        c.execute(taskList, s);
        assertEquals(0, taskList.size());
    }
}