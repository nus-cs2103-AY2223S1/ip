package duke.operations;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void setupTest() {
        File testDir = new File("testDir");
        File testFile = new File("testDir/testfile.txt");
        if (testFile.exists()) {
            testFile.delete();
            testDir.delete();
        }
        Storage storageTest = new Storage("testDir/testfile.txt");
        try {
            storageTest.setupDirectory();
        } catch (DukeException e) {
        } finally {
            assertEquals(true, testFile.exists());
        }
    }

    @Test
    public void saveTest() {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new TodoTask("Setup unit tests"));
        tasks.addTask(new DeadlineTask("Finish week 3 tasks", "2022-08-28"));
        tasks.addTask(new EventTask("Lecture", "2022-08-29"));

        String expected = new TodoTask("Setup unit tests").saveFileFormat() + "\n"
                        + new DeadlineTask("Finish week 3 tasks", "2022-08-28").saveFileFormat() + "\n"
                        + new EventTask("Lecture", "2022-08-29").saveFileFormat() + "\n";

        File testDir = new File("testDir");
        File testFile = new File("testDir/testfile.txt");
        if (testFile.exists()) {
            testFile.delete();
            testDir.delete();
        }

        Storage storagetest = new Storage("testDir/testfile.txt");
        StringBuilder actualText = new StringBuilder();
        try {
            storagetest.setupDirectory();
            storagetest.updateSave(tasks);
            Scanner sc = new Scanner(testFile);
            while (sc.hasNext()) {
                actualText.append(sc.nextLine() + "\n");
            }
        } catch (DukeException e) {
        } catch (FileNotFoundException e) {
        } finally {
            assertEquals(expected, actualText.toString());
        }
    }
}
