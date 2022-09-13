package nyanduke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import nyanduke.NyanDukeException;

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
    public void testDelete_success() throws NyanDukeException {
        Task testTask1 = new Todo("1");
        Task testTask2 = new Todo("2");
        Task testTask3 = new Todo("3");
        TaskList testList = new TaskList(testTask1, testTask2, testTask3);

        TaskList expectedRemainingTasks = new TaskList(testTask2);

        ArrayList<Task> expectedDeletedTasks = new ArrayList<>();
        expectedDeletedTasks.add(testTask1);
        expectedDeletedTasks.add(testTask3);

        Integer[] testArray = {3, 1};
        ArrayList<Task> actualDeletedTasks = testList.delete(testArray);

        assertEquals("Now you have 1 task in your list.", testList.getCountStatement());
        assertEquals(expectedRemainingTasks, testList);
        assertEquals(expectedDeletedTasks, actualDeletedTasks);
    }

    @Test
    public void testDelete_exceptionThrown() {
        try {
            TaskList testList = new TaskList();
            Integer[] testArray = {0};
            testList.delete(testArray);
            fail();
        } catch (NyanDukeException e) {
            assertEquals("There's a specified task that does not exist ;-;\n",
                    e.getMessage());
        }
    }

    @Test
    public void testMark_success() throws NyanDukeException {
        Task testTask1 = new Todo("1");
        TaskList testList = new TaskList(testTask1);

        Integer[] testArray = {1};
        ArrayList<Task> markedTasks = testList.mark(testArray);
        Task actualTask = markedTasks.get(0);

        assertEquals("Now you have 1 task in your list.", testList.getCountStatement());
        assertEquals(testTask1, actualTask);
        assertEquals("[T][X] 1", actualTask.toString());
    }

    @Test
    public void testMark_exceptionThrown() {
        try {
            TaskList testList = new TaskList();
            Integer[] testArray = {-1};
            testList.mark(testArray);
            fail();
        } catch (NyanDukeException e) {
            assertEquals("There's a specified task that does not exist ;-;\n",
                    e.getMessage());
        }
    }

    @Test
    public void testUnmark_success() throws NyanDukeException {
        Task testTask1 = new Todo("1");
        TaskList testList = new TaskList(testTask1);

        Integer[] testArray = {1};
        testList.mark(testArray);
        ArrayList<Task> unmarkedTasks = testList.unmark(testArray);
        Task actualTask = unmarkedTasks.get(0);

        assertEquals("Now you have 1 task in your list.", testList.getCountStatement());
        assertEquals(testTask1, actualTask);
        assertEquals("[T][ ] 1", actualTask.toString());
    }

    @Test
    public void testUnmark_exceptionThrown() {
        try {
            TaskList testList = new TaskList();
            Integer[] testArray = {1};
            testList.unmark(testArray);
            fail();
        } catch (NyanDukeException e) {
            assertEquals("There's a specified task that does not exist ;-;\n",
                    e.getMessage());
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

        TaskList actualList = testList.getAllOnDate(LocalDate.parse("2022-09-01"));
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testAllToData() {
        TaskList testList = new TaskList();
        testList.add(new Todo("test"));
        testList.add(new Deadline("test", "2022-09-01"));
        testList.add(new Event("test", "noon"));

        String[] actualOutput = testList.convertAllToData();
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

        String[] actualOutput = testList.convertAllToString();
        String[] expectedOutput = {"[T][ ] test", "[D][ ] test (by: Sep 1 2022)", "[E][ ] test (at: noon)"};

        for (int i = 0; i < 3; i++) {
            assertEquals(expectedOutput[i], actualOutput[i]);
        }
    }

    @Test
    public void testAllContaining() {
        TaskList testList = new TaskList();
        TaskList expectedOutput = new TaskList();
        Task testTask1 = new Todo("test");
        Task testTask2 = new Deadline("testy", "2022-09-01");
        Task testTask3 = new Todo("hidden ninja");
        Task testTask4 = new Event(" i test +ve", "noon");

        testList.add(testTask1);
        testList.add(testTask2);
        testList.add(testTask3);
        testList.add(testTask4);

        expectedOutput.add(testTask1);
        expectedOutput.add(testTask2);
        expectedOutput.add(testTask4);

        TaskList actualOutput = testList.getAllContaining("test");

        assertEquals(expectedOutput, actualOutput);
    }
}
