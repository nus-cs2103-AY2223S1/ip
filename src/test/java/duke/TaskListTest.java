package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Tests the tasklist class and associated methods.
 *
 * @author Yuvaraj Kumaresan
 */
public class TaskListTest {

    /**
     * Tasklist to test.
     */
    TaskList testList = new TaskList(new ArrayList<Task>());

    /**
     * Test Todo.
     */
    ToDo testTaskOne = new ToDo("This is also a test task");


    /**
     * Test Deadline.
     */
    Deadline testTaskTwo = new Deadline("This is a deadline", LocalDateTime.now());

    /**
     * Test Event.
     */
    Event testTaskThree = new Event("This is an event", "12-11-2020");

    /**
     * Tests the addTodo method.
     */
    @Test
    public void addTodoTest() {
        testList.addTodo(testTaskOne);
        assertEquals(1, testList.arrayList.size());
    }

    /**
     * Tests the addDeadline method.
     */
    @Test
    public void addDeadlineTest() {
        testList.addDeadline(testTaskTwo);
        assertEquals(1, testList.arrayList.size());
    }

    /**
     * Tests the addEvent method.
     */
    @Test
    public void addEventTest() {
        testList.addEvent(testTaskThree);
        assertEquals(1, testList.arrayList.size());
    }

    /**
     * Tests the delete method.
     */
    @Test
    public void deleteTest() {
        testList.addTodo(testTaskOne);
        testList.addDeadline(testTaskTwo);
        testList.addEvent(testTaskThree);
        testList.delete(2);
        assertEquals(2, testList.arrayList.size());
    }
}