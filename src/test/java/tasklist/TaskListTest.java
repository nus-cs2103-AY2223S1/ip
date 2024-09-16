package tasklist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import exceptions.DukeException;
import task.Event;
import task.Task;

public class TaskListTest {

    private TaskList tl = new TaskList();

    @Test
    public void addTask_addTaskToList_numberOfTasksInTaskListIncrements() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("description"));
        assertEquals(1, tl.getSize());

        tl.addTask(null);
        assertEquals(1, tl.getSize());
    }

    @Test
    public void deleteTask_deleteNonExistentTask_throwsDukeException() {
        Assertions.assertThrows(DukeException.class, () -> tl.deleteTask(10000));
    }

    @Test
    public void checkIsToday_taskIsToday_returnsCorrectStatus() {
        LocalDate todaysDate = LocalDate.now();
        TaskList tl = new TaskList();
        Event e = new Event("description", todaysDate);
        tl.addTask(e);
        try {
            assertTrue(tl.checkIsToday(1));
        } catch (DukeException de) {
            fail();
        }
    }

    @Test
    public void getLongDescription_containsSingleTask_longDescIsCorrect() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("todo"));
        try {
            assertEquals("Task todo is not completed yet", tl.getLongDescription(1));
        } catch (DukeException de) {
            fail();
        }
    }

    @Test
    public void getSize_numberOfTasksInListChanges_correctSizeReturned() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("todo"));
        assertEquals(1, tl.getSize());
        try {
            tl.deleteTask(1);
            assertEquals(0, tl.getSize());
        } catch (DukeException de) {
            fail();
        }
    }

    @Test
    public void getContents_differentScenarios_correctStringBuffersReturned() {
        // empty TaskList
        TaskList tl = new TaskList();
        String actualString = tl.getContents().toString();
        assertTrue(actualString.equals(""));

        // one task in list
        tl.addTask(new Task("todo"));
        actualString = tl.getContents().toString();
        assertTrue(actualString.equals("1. [T][ ] todo\n"));
    }

    @Test
    public void markTask_singleTaskInList_correctlyMarked() {
        TaskList tl = new TaskList();
        tl.addTask(new Task("todo"));
        tl.markTask(1);
        try {
            assertTrue(tl.getLongDescription(1).equals("Task todo is completed"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTask_invalidIndex_throwsDukeException() {
        assertThrows(DukeException.class, () -> tl.getTask(10000));
    }

    @Test
    public void findTasks_noMatchingKeyword_returnsEmptyTaskList() {
        assertEquals(0, tl.findTasks("keyword").getSize());
    }
}
