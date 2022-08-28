package skylark.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import skylark.skylark.SkylarkException;
import skylark.task.Deadline;
import skylark.task.Event;
import skylark.task.Task;
import skylark.task.ToDo;

public class StorageTest {

    @Test
    public void readFromFile_whenFileNotExist_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "empty.txt";
        Storage storage = new Storage(filePath);
        ArrayList<Task> taskArrayList = storage.readFromFile();
        assertEquals(0, taskArrayList.size());
    }

    @Test
    public void saveToFile_whenArrayExist_success() {
        String filePath = System.getProperty("user.dir")
                + File.separator + "test" + File.separator + "result.txt";
        Storage storage = new Storage(filePath);
        ArrayList<Task> taskArrayList = new ArrayList<>();

        try {
            taskArrayList.add(new ToDo("Make Coffee"));
            taskArrayList.add(new ToDo("Make Cereal"));
            taskArrayList.add(new Event("Attend Splashdown", "2022-08-21 1800"));
            taskArrayList.add(new Event("Attend Lecture", "2022-08-21 1800"));
            taskArrayList.add(new Deadline("Finish CS2100", "2022-08-21 1800"));
            taskArrayList.add(new Deadline("Finish CS2103", "2022-08-21 1800"));
            storage.saveToFile(taskArrayList);
        } catch (SkylarkException skylarkException) {
            fail();
        }

    }
}
