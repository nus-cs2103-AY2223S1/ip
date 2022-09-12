package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.CommandType;
import duke.task.Event;

class TaskListTest {
    private static final File FILE = new File(Duke.FILE_PATH);
    private final Storage storage = new Storage(Duke.FILE_PATH);
    private final TaskList taskList = new TaskList(storage);

    /**
     * Deletes the file that was created.
     */
    @AfterAll
    static void tearDown() {
        if (FILE.exists() && !FILE.delete()) {
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
        Event newTask = new Event("event project meeting ".substring(5).strip(), CommandType.EVENT, dateTime);
        taskList.addToTaskList(newTask);
    }

    /**
     * Tests that the task was added successfully.
     * @throws FileNotFoundException if the file is not found
     */
    @Test
    public void addToTaskList_newTask_addSuccessfully() throws FileNotFoundException {
        assertEquals("[E][X] project meeting (at: 31 Dec 2023 23:59)", taskList.getTaskString(0));
        Scanner fileScanner = new Scanner(FILE);
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
        Scanner fileScanner = new Scanner(FILE);
        assertEquals("event project meeting /at 2023-12-31 23:59", fileScanner.nextLine());
        assertEquals("mark 1", fileScanner.nextLine());
    }
}
