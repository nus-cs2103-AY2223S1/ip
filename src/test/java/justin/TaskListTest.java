package justin;

import justin.task.Deadline;
import justin.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void taskListTest1() {
        TaskList taskList = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("homework", false);
        taskList.addTask(todo);
        Deadline deadline = new Deadline("assignment", false, "2022-10-12", "23:59");
        taskList.addTask(deadline);
        try {
            taskList.mark(1);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals("T | Done! | homework", taskList.getTask(1).toString());
    }

    @Test
    public void taskListTest2() {
        TaskList taskList = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("Homework", false);
        taskList.addTask(todo);
        Deadline deadline = new Deadline("assignment", false, "2022-10-12", "23:59");
        taskList.addTask(deadline);
        try {
            taskList.delete(1);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void taskListTest3() {
        TaskList tasklist = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("Homework", false);
        tasklist.addTask(todo);
        Deadline deadline = new Deadline("assignment", false, "2022-10-12", "23:59");
        tasklist.addTask(deadline);
        try {
            tasklist.delete(2);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals("T | Undone | Homework", tasklist.getTask(1).toString());
    }

    @Test
    public void taskListTest4() {
        TaskList taskList = new TaskList(new ArrayList<>());
        ToDo todo = new ToDo("homework", true);
        taskList.addTask(todo);
        Deadline deadline = new Deadline("assignment", false, "2022-10-12", "23:59");
        taskList.addTask(deadline);
        try {
            taskList.unmark(1);
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assertEquals("T | Undone | homework", taskList.getTask(1).toString());
    }
}
