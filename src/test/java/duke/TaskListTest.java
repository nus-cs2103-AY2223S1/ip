package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.CommandType;
import duke.task.Event;
import duke.tasklist.TaskList;

class TaskListTest {
    private static final File FILE = new File(Storage.FILE_PATH);
    private final TaskList taskList = new TaskList();

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
     */
    @Test
    public void addToTaskList_newTask_addSuccessfully() {
        assertEquals("[E][ ] project meeting (at: 31 Dec 2023 23:59)", taskList.getTaskString(0));
    }

    /**
     * Tests that the task was marked done successfully.
     */
    @Test
    public void markTaskAsDone_eventTask_markDoneSuccessfully() {
        taskList.markTaskAsDone(0);
        assertEquals("[E][X] project meeting (at: 31 Dec 2023 23:59)", taskList.getTaskString(0));
    }

    /**
     * Tests that the task was marked not done successfully.
     */
    @Test
    public void markTaskAsDone_eventTask_markNotDoneSuccessfully() {
        taskList.markTaskAsNotDone(0);
        assertEquals("[E][ ] project meeting (at: 31 Dec 2023 23:59)", taskList.getTaskString(0));
    }

    /**
     * Tests that the task was deleted successfully.
     */
    @Test
    public void markTaskAsDone_eventTask_deleteSuccessfully() {
        taskList.removeTask(0);
        assertEquals(0, taskList.sizeOfList());
    }
}
