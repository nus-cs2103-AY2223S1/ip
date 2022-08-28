package unc;

import unc.task.Deadline;
import unc.task.Event;
import unc.task.Task;
import unc.task.Todo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    public static ArrayList<Task> testList () {
        ArrayList<Task> temp = new ArrayList<>();
        try {
            temp.add(new Todo("Fly", "random"));
            temp.add(new Event("Celebrate", "2001-09-11", "true"));
            temp.add(new Deadline("Trade", "2022-08-02"));
        } catch (UncException uncException) {
            System.out.println("Error occurred in setup.");
        }
        return temp;
    }

    @Test
    public void getTest() {
        TaskList taskList = new TaskList(testList());
        String expected = "[E][X] Celebrate (at: 11-09-2001)";
        assertEquals(expected, taskList.get(1).toString());
    }

    @Test
    public void deleteTaskList() {
        TaskList tasklist = new TaskList(testList());
        tasklist.delete(1);
        String expected = "[D][ ] Trade (by: 02-08-2022)";
        assertEquals(expected, tasklist.get(1).toString());
    }

    @Test
    public void markTaskList() {
        TaskList tasklist = new TaskList(testList());
        tasklist.markAsDone(2);
        String expected = "[D][X] Trade (by: 02-08-2022)";
        assertEquals(expected, tasklist.get(2).toString());
    }

    @Test
    public void unMarkTaskList() {
        TaskList tasklist = new TaskList(testList());
        tasklist.markAsNotDone(1);
        String expected = "[E][ ] Celebrate (at: 11-09-2001)";
        assertEquals(expected, tasklist.get(1).toString());
    }

}
