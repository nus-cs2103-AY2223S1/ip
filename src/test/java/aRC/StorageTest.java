package arc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class StorageTest {

    @Test
    public void fileNotFound_invalidFilePath_createFile() {
        String fileRelativePath = "dataTest/aRC.txt";

        boolean hasFileStart = new File(fileRelativePath).exists();
        new Storage(fileRelativePath).load();
        boolean hasFileEnd = new File(fileRelativePath).exists();

        assertFalse(hasFileStart);
        assertTrue(hasFileEnd);

        File testData = new File(fileRelativePath);
        testData.delete();
        testData.getParentFile().delete();
    }

    @Test
    public void loadTasks_testDataFile_tasksLoaded() {
        String fileRelativePath = "dataTest1/aRC.txt";

        File testDataFile = new File(fileRelativePath);
        Storage storageTest = new Storage(fileRelativePath);
        storageTest.load();

        int numberOfTestTests = 5;
        ArrayList<String> testData = new ArrayList<>();

        for (int i = 0; i < numberOfTestTests; i++) {
            testData.add(new Todo(Integer.toString(i), false).toFileFormat());
        }

        try {
            FileWriter fw = new FileWriter(testDataFile);
            fw.write(String.join("\n", testData));
            fw.close();

            TaskList taskListTest = new TaskList(storageTest.load());
            assertEquals(numberOfTestTests, taskListTest.numTasks());
        } catch (IOException e) {
            fail();
        } finally {
            testDataFile.delete();
            testDataFile.getParentFile().delete();
        }
    }
}
