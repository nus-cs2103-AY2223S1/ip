package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

public class TaskListTest {

    @Test
    public void addTest1() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("Task 1"));
        int actual = taskList.size();
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void addTest2() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("Task 1"));
        taskList.add(new TodoTask("Task 2"));
        taskList.add(new TodoTask("Task 3"));
        int actual = taskList.size();
        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void listTest1() {
        TaskList taskList = new TaskList();
        String actual = taskList.list();
        String expected = "No tasks yet";
        assertEquals(expected, actual);
    }

    @Test
    public void listTest2() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("Task 1"));
        taskList.add(new DeadlineTask("Task 2", "13072000"));
        taskList.add(new EventTask("Task 3", "01012022", "1234"));
        taskList.mark(3);
        String actual = taskList.list();
        String expected = "[T][0] Task 1\n[D][0] Task 2 | 13 Jul 2000\n[E][1] Task 3 | 01 Jan 2022 1234";
        assertEquals(expected, actual);
    }

    @Test
    public void markTest() {
        TaskList taskList = new TaskList();
        taskList.add(new TodoTask("Task 1"));
        taskList.add(new DeadlineTask("Task 2", "13072000"));
        taskList.add(new EventTask("Task 3", "01012022", "1234"));
        taskList.mark(1);
        boolean actual = taskList.getTask(1).isCompleted();
        boolean expected = true;
        assertEquals(expected, actual);
    }


}
