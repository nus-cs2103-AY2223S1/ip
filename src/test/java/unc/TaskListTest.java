package unc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import unc.task.Deadline;
import unc.task.Event;
import unc.task.Task;
import unc.task.Todo;

public class TaskListTest {

    public static ArrayList<Task> testList() {
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
        String expected = "[E][X] Celebrate (at: 11 Sep 2001)";
        assertEquals(expected, taskList.get(1).toString());
    }

    @Test
    public void deleteTaskList() {
        TaskList tasklist = new TaskList(testList());
        tasklist.delete(1);
        String expected = "[D][ ] Trade (by: 02 Aug 2022)";
        assertEquals(expected, tasklist.get(1).toString());
    }

}
