package duke;

import duke.task.Event;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskListTest {
    private final Storage storage = new Storage(Duke.filePath);
    private final TaskList taskList = new TaskList(storage);
    private static final File file = new File(Duke.filePath);

    /**
     * Deletes the file that was created.
     */
    @AfterAll
    static void tearDown() {
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Could not delete file");
        }
    }

    /**
     *
     */
    @BeforeEach
    void addEventTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse("2023-12-31 23:59", formatter);
        Event newTask = new Event("event project meeting ".substring(5).strip(), Command.EVENT, dateTime);
        taskList.addToTaskList(newTask);
    }

    /**
     * Tests that the task was added successfully.
     * @throws FileNotFoundException if the file is not found
     */
    @Test
    public void addToTaskList_newTask_addSuccessfully() throws FileNotFoundException {
        assertEquals("[E][X] project meeting (at: 31 Dec 2023 23:59)", taskList.getTaskString(0));
        Scanner fileScanner = new Scanner(file);
        assertEquals("event project meeting /at 2023-12-31 23:59", fileScanner.nextLine());
    }

    /**
     * Tests that the task was marked done successfully.
     * @throws FileNotFoundException if the file is not found
     */
    @Test
    public void markTaskAsDone_eventTask_markSuccessfully() throws FileNotFoundException {
        taskList.markTaskAsDone(0);
        assertEquals("[E][X] project meeting (at: 31 Dec 2023 23:59)", taskList.getTaskString(0));
        Scanner fileScanner = new Scanner(file);
        assertEquals("event project meeting /at 2023-12-31 23:59", fileScanner.nextLine());
        assertEquals("mark 1", fileScanner.nextLine());
    }
}