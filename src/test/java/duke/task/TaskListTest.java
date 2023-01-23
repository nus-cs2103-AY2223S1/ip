package duke.task;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.DukeException;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList sut = null;
    private final Task task1 = new ToDo("Task 1");
    private final Task task2 = new Event("Task 2", LocalDate.of(2022, 7, 25));
    private final Task task3 = new Deadline("Task 3", LocalDate.of(2022, 1, 25));

    @Test
    public void addTask_toDoTask_success() {
        sut = new TaskList();
        sut.addTask(task1);
        assertEquals(1, sut.getNumOfTask());
        sut.addTask(task2);
        assertEquals(2, sut.getNumOfTask());
        sut.addTask(task3);
        assertEquals(3, sut.getNumOfTask());
    }

    @Test
    public void getTaskByIndex_validIndex_success() {
        try {
            sut = fillTaskList();
            assertEquals(task1, sut.getTaskByIndex(1));
            assertEquals(task2, sut.getTaskByIndex(2));
            assertEquals(task3, sut.getTaskByIndex(3));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getTaskByIndex_invalidIndex_fail() {
        try {
            sut = fillTaskList();
            sut.getTaskByIndex(4);
            fail();
        } catch (DukeException e) {
            assertEquals("The number you entered is not within the valid range.", e.getMessage());
        }
    }

    @Test
    public void markTaskByIndex_validArgs_success() {
        try {
            sut = fillTaskList();
            Task toAdd = new ToDo("Testing mark by index");
            sut.addTask(toAdd);
            sut.markTaskByIndex(4, true);
            assertEquals("[X]", toAdd.getStatusIcon());
            sut.markTaskByIndex(4, false);
            assertEquals("[ ]", toAdd.getStatusIcon());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void markTaskByIndex_invalidArgs_fail() {
        try {
            sut = fillTaskList();
            Task toAdd = new ToDo("Testing mark by index");
            sut.addTask(toAdd);
            sut.markTaskByIndex(-1, true);
            fail();
        } catch (DukeException e) {
            assertEquals("The number you entered is not within the valid range.", e.getMessage());
        }
    }

    @Test
    public void deleteTaskByIndex_validArg_success() {
        try {
            sut = fillTaskList();
            sut.deleteTaskByIndex(1);
            assertEquals(2, sut.getNumOfTask());
            assertNotEquals(sut.getTaskByIndex(1), task1);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void deleteTaskByIndex_invalidArgs_fail() {
        try {
            sut = fillTaskList();
            Task toAdd = new ToDo("Testing delete by index");
            sut.addTask(toAdd);
            sut.deleteTaskByIndex(-1);
            fail();
        } catch (DukeException e) {
            assertEquals("The number you entered is not within the valid range.", e.getMessage());
        }
    }

    @Test
    public void getTaskListWithKeyword_existingKeyword_success() {
        sut = fillTaskList();
        assertEquals(3, sut.getTaskListWithKeyword("Task").getNumOfTask());
        assertEquals(3, sut.getTaskListWithKeyword("task").getNumOfTask());
        assertEquals(1, sut.getTaskListWithKeyword("task 1").getNumOfTask());
    }

    @Test
    public void sortByDate_true_success() {
        try {
            sut = fillTaskList();
            Task task4 = new Deadline("Task 4", LocalDate.of(2019, 7, 23));
            Task task5 = new Event("Task 5", LocalDate.of(2021, 3, 13));
            Task task6 = new Event("Task 6", LocalDate.of(2021, 3, 5));
            sut.addTask(task4);
            sut.addTask(task5);
            sut.addTask(task6);
            sut.sortByDate(true);
            assertEquals(task4, sut.getTaskByIndex(1));
            assertEquals(task6, sut.getTaskByIndex(2));
            assertEquals(task5, sut.getTaskByIndex(3));
            assertEquals(task3, sut.getTaskByIndex(4));
            assertEquals(task2, sut.getTaskByIndex(5));
            assertEquals(task1, sut.getTaskByIndex(6));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void sortByDate_false_success() {
        try {
            sut = fillTaskList();
            Task task4 = new Deadline("Task 4", LocalDate.of(2019, 7, 23));
            Task task5 = new Event("Task 5", LocalDate.of(2021, 3, 13));
            Task task6 = new Event("Task 6", LocalDate.of(2021, 3, 5));
            sut.addTask(task4);
            sut.addTask(task5);
            sut.addTask(task6);
            sut.sortByDate(false);
            assertEquals(task2, sut.getTaskByIndex(1));
            assertEquals(task3, sut.getTaskByIndex(2));
            assertEquals(task5, sut.getTaskByIndex(3));
            assertEquals(task6, sut.getTaskByIndex(4));
            assertEquals(task4, sut.getTaskByIndex(5));
            assertEquals(task1, sut.getTaskByIndex(6));
        } catch (DukeException e) {
            fail();
        }

    }

    private TaskList fillTaskList() {
        TaskList res = new TaskList();
        res.addTask(task1);
        res.addTask(task2);
        res.addTask(task3);
        return res;
    }

}
