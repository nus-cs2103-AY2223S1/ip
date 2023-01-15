package blink;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import blink.task.Deadlines;
import blink.task.Events;
import blink.task.Task;
import blink.task.ToDos;

public class TaskListTest {

    public static ArrayList<Task> tempList() {
        ArrayList<Task> temp = new ArrayList<>();
        temp.add(new Deadlines("Study 1", "2022-08-23"));
        temp.add(new Events("Study 2", "2022-08-23"));
        temp.add(new ToDos("Study 3"));
        return temp;
    }

    @Test
    public void listTaskList() {
        TaskList taskList = new TaskList(TaskListTest.tempList());
        String expected = "There is a total of 3 tasks currently:\n"
                + "1: [D][ ] Study 1 (by: AUGUST 23 2022 TUESDAY)\n"
                + "2: [E][ ] Study 2 (at: AUGUST 23 2022 TUESDAY)\n"
                + "3: [T][ ] Study 3";
        assertEquals(expected, taskList.listTask());
    }

    @Test
    public void deleteTaskList() {
        TaskList tasklist = new TaskList(TaskListTest.tempList());
        String expected = "[T][ ] Study 3";
        assertEquals(expected, tasklist.deleteTask(3).toString());
    }

    @Test
    public void correctTaskListLength() {
        TaskList tasklist = new TaskList(TaskListTest.tempList());
        assertEquals(3, tasklist.length());
    }

    @Test
    public void markTaskList() {
        TaskList tasklist = new TaskList(TaskListTest.tempList());
        tasklist.mark(3);
        String expected = "There is a total of 3 tasks currently:\n"
                + "1: [D][ ] Study 1 (by: AUGUST 23 2022 TUESDAY)\n"
                + "2: [E][ ] Study 2 (at: AUGUST 23 2022 TUESDAY)\n"
                + "3: [T][X] Study 3";
        assertEquals(expected, tasklist.listTask());
    }

    @Test
    public void unMarkTaskList() {
        TaskList tasklist = new TaskList(TaskListTest.tempList());
        tasklist.mark(1);
        tasklist.mark(2);
        tasklist.mark(3);
        tasklist.unMark(3);
        String expected = "There is a total of 3 tasks currently:\n"
                + "1: [D][X] Study 1 (by: AUGUST 23 2022 TUESDAY)\n"
                + "2: [E][X] Study 2 (at: AUGUST 23 2022 TUESDAY)\n"
                + "3: [T][ ] Study 3";
        assertEquals(expected, tasklist.listTask());
    }

    @Test
    public void filterTaskList() {
        TaskList tasklist = new TaskList(TaskListTest.tempList());
        String expected = "There is a total of 2 tasks currently:\n"
                + "1: [D][ ] Study 1 (by: AUGUST 23 2022 TUESDAY)\n"
                + "2: [E][ ] Study 2 (at: AUGUST 23 2022 TUESDAY)";
        LocalDate date = LocalDate.parse("2022-08-23");
        assertEquals(expected, new TaskList(tasklist.filter(date)).listTask());
    }
}
