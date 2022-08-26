package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class TaskListTest {
    @Test
    public void testGetCountStatement_notOne() {
        TaskList testList = new TaskList();
        assertEquals("Now you have 0 tasks in your list.", testList.getCountStatement());
    }

    @Test
    public void testGetCountStatement_one() {
        Task testTask = new Todo("test");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(testTask);
        TaskList testTaskList = new TaskList(tasks);
        assertEquals("Now you have 1 task in your list.", testTaskList.getCountStatement());
    }

    @Test
    public void testAdd() {
        TaskList testList = new TaskList();
        Task testTask = new Todo("test");
        testList.add(testTask);
        assertEquals("Now you have 1 task in your list.", testList.getCountStatement());
    }

    @Test
    public void testDelete_success() throws DukeException {
        TaskList testList = new TaskList();
        Task expectedTask = new Todo("test");
        testList.add(expectedTask);
        Task actualTask = testList.delete(1);
        assertEquals("Now you have 0 tasks in your list.", testList.getCountStatement());
        assertEquals(expectedTask, actualTask);
    }

    @Test
    public void testDelete_exceptionThrown() {
        try {
            TaskList testList = new TaskList();
            testList.delete(1);
            fail();
        } catch (DukeException e) {
            assertEquals("The specified task does not exist.", e.getMessage());
        }
    }

    @Test
    public void testMark_success() throws DukeException {
        TaskList testList = new TaskList();
        Task expectedTask = new Todo("test");
        testList.add(expectedTask);
        Task actualTask = testList.mark(1);
        assertEquals("Now you have 1 task in your list.", testList.getCountStatement());
        assertEquals(expectedTask, actualTask);
        assertEquals("[T][X] test", actualTask.toString());
    }

    @Test
    public void testMark_exceptionThrown() {
        try {
            TaskList testList = new TaskList();
            testList.mark(1);
            fail();
        } catch (DukeException e) {
            assertEquals("The specified task does not exist.", e.getMessage());
        }
    }

    @Test
    public void testUnmark_success() throws DukeException {
        TaskList testList = new TaskList();
        Task expectedTask = new Todo("test");
        testList.add(expectedTask);
        testList.mark(1);
        Task actualTask = testList.unmark(1);
        assertEquals("Now you have 1 task in your list.", testList.getCountStatement());
        assertEquals(expectedTask, actualTask);
        assertEquals("[T][ ] test", actualTask.toString());
    }

    @Test
    public void testUnmark_exceptionThrown() {
        try {
            TaskList testList = new TaskList();
            testList.unmark(1);
            fail();
        } catch (DukeException e) {
            assertEquals("The specified task does not exist.", e.getMessage());
        }
    }

    @Test
    public void testAllOnDate() {
        TaskList testList = new TaskList();
        Task todo = new Todo("not on date");
        Task deadlineOnDate = new Deadline("on date", "2022-09-01");
        Task deadlineNotOnDate = new Deadline("not on date", "2022-09-02");
        Task eventOnDate = new Event("on date", "2022-09-01 0800");
        Task eventNotOnDate = new Event("not on date", "noon");

        testList.add(todo);
        testList.add(deadlineOnDate);
        testList.add(deadlineNotOnDate);
        testList.add(eventOnDate);
        testList.add(eventNotOnDate);

        TaskList expectedList = new TaskList();
        expectedList.add(deadlineOnDate);
        expectedList.add(eventOnDate);

        TaskList actualList = testList.allOnDate(LocalDate.parse("2022-09-01"));
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testAllToData() {
        TaskList testList = new TaskList();
        testList.add(new Todo("test"));
        testList.add(new Deadline("test", "2022-09-01"));
        testList.add(new Event("test", "noon"));

        String[] actualOutput = testList.allToData();
        String[] expectedOutput = {"T |   | test\n", "D |   | test | Sep 1 2022\n", "E |   | test | noon\n"};

        for (int i = 0; i < 3; i++) {
            assertEquals(expectedOutput[i], actualOutput[i]);
        }
    }

    @Test
    public void testAllToString() {
        TaskList testList = new TaskList();
        testList.add(new Todo("test"));
        testList.add(new Deadline("test", "2022-09-01"));
        testList.add(new Event("test", "noon"));

        String[] actualOutput = testList.allToString();
        String[] expectedOutput = {"[T][ ] test", "[D][ ] test (by: Sep 1 2022)", "[E][ ] test (at: noon)"};

        for (int i = 0; i < 3; i++) {
            assertEquals(expectedOutput[i], actualOutput[i]);
        }
    }
}
