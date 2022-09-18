package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

class StorageTest {
    /**
     * A nested class containing test methods if the files exist
     */
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class FileExists {
        private final String currentTasksString = "todo borrow book\nlist\nmark 1\n"
                + "deadline return book /by 2022-08-24 14:00\nevent project meeting /at 2023-12-31 23:59\n"
                + "todo\nblah\ndelete 2\nlist\ndeadline return book /by 2022-08-24 14:00\nbye\n";
        private final String previousTasksString = "todo borrow book\nlist\nmark 1\n"
                + "deadline return book /by 2022-08-24 14:00\nevent project meeting /at 2023-12-31 23:59\n"
                + "todo\nblah\ndelete 2\nlist\ndeadline return book /by 2022-08-24 14:00\nbye\n";

        /**
         * Before each test, create the files containing the task lists
         * @throws IOException if the file is not fouund
         */
        @BeforeAll
        void setUpFiles() throws IOException {
            Files.writeString(Paths.get(Storage.FILE_PATH), currentTasksString);
            Files.writeString(Paths.get(Storage.PREVIOUS_TASKS_FILE_PATH), previousTasksString);
        }
        /**
         * Before each test, create the files containing the task lists
         */
        @AfterAll
        void tearDown() {
            final File file = new File(Storage.FILE_PATH);
            final File file2 = new File(Storage.PREVIOUS_TASKS_FILE_PATH);
            if (file.exists() && !file.delete()) {
                throw new RuntimeException("Could not delete current tasks file");
            }
            if (file2.exists() && !file2.delete()) {
                throw new RuntimeException("Could not delete previous tasks file");
            }
        }
        /**
         * Tests that the current tasks file was parsed successfully.
         */
        @Test
        public void parseCurrentTasks_fileExists_fileReadSuccessful() {
            Storage storage = new Storage();
            Optional<Scanner> optionalScanner = storage.getScannerForTasksFile();
            if (optionalScanner.isEmpty()) {
                fail("No file detected");
            }
            Scanner scanner = optionalScanner.get();
            StringBuilder stringReadFromFile = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringReadFromFile.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            assertEquals(stringReadFromFile.toString(), currentTasksString);
        }

        /**
         * Tests that the previous tasks file was parsed successfully.
         */
        @Test
        public void parsePreviousTasks_fileExists_fileReadSuccessful() {
            Storage storage = new Storage();
            Optional<Scanner> optionalScanner = storage.getScannerForTasksFile();
            if (optionalScanner.isEmpty()) {
                fail("No file detected");
            }
            Scanner scanner = optionalScanner.get();
            StringBuilder stringReadFromFile = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringReadFromFile.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
            assertEquals(stringReadFromFile.toString(), previousTasksString);
        }
    }

    /**
     * Class that checks when there is no previous tasks file
     */
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class FileDoesNotExist {
        /**
         * Tests that the previous tasks file was parsed successfully.
         */
        @Test
        public void parsePreviousTasks_fileDoesNotExist_methodReturnsEmptyOptional() {
            Storage storage = new Storage();
            assertTrue(storage.getScannerForPreviousTasksFile().isEmpty());
        }
    }
}
