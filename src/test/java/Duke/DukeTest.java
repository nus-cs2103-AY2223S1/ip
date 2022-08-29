package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Duke.Tasks.Task;
import Duke.Tasks.ToDo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DukeTest {
    @Test
    public void getTaskTest() {
        ToDo task1 = new ToDo("SSSS");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(task1);


    }
}