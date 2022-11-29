package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class StorageTest {
    private static final String FOLDER_DIRECTORY = "storage";
    private static final String FILE_NAME = "testStorage";
    private static final String FILE_DIRECTORY = "storage/testStorage.txt";

    // Solution below adapted from https://www.baeldung.com/java-delete-directory
    private static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    @Test
    public void createEmptyStorageTest() {
        Storage storage = new Storage(FOLDER_DIRECTORY, FILE_NAME);
        storage.writeAllToStorage(new TaskList());
        File f = new File(FILE_DIRECTORY);
        assertTrue(f.exists());
    }

    @Test
    public void writeSimpleStorageTest() {
        Storage storage = new Storage(FOLDER_DIRECTORY, FILE_NAME);
        TaskList tl = new TaskList();
        tl.addTask(new Todo("test", true));
        tl.addTask(new Todo("test", true));
        tl.addTask(new Todo("test", true));
        storage.writeAllToStorage(new TaskList());
        File f = new File(FILE_DIRECTORY);
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                assertEquals("Todo|test|0| ", sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void cleanUp() {
        deleteDirectory(new File(FOLDER_DIRECTORY));
    }
}
