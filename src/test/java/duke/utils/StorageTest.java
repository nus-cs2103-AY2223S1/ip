package duke.utils;

import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testSaveAndLoadData() {
        List<Task> taskList = new ArrayList<>();

        taskList.add(new Todo("blablabla"));
        taskList.add(new Todo("albalbalb"));
        taskList.add(new Todo("blankblank"));

        File testFile = new File("testdata.txt");
        Storage storage = new Storage(testFile);

        storage.saveToFile(taskList);
        List<Task> loadedTasks = storage.loadFromFile();

        assertEquals(loadedTasks.size(), 3);

        testFile.delete();
    }

}
