package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.utilities.DukeException;

public class TaskListTest {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Todo todo1;
    private Todo todo2;
    private Todo todo3;

    {
        try {
            todo1 = new Todo("first todo.");
            todo2 = new Todo("second todo.");
            todo3 = new Todo("third todo.");
        } catch (DukeException e) {
            throw new RuntimeException(e);
        }

        tasks.add(todo1);
        tasks.add(todo2);
    }

    private TaskList taskList = new TaskList(tasks);

    @Test
    public void getTasks_arraylistOfTasks_correct() {
        assertEquals(tasks, taskList.getTasks());
    }

    @Test
    public void getNumberOfTasks_numberOfTasks_correct() {
        assertEquals(2, taskList.getNumberOfTasks());
    }

    @Test
    public void addTask_addNewTask_correct() {
        taskList.addTask(todo3);
        assertEquals(3, taskList.getNumberOfTasks());
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            taskList.deleteTask(-1);
        });

        assertEquals("You must specify which task to delete!", de.getMessage());
    }

    @Test
    public void deleteTask_deletesTask_correct() {
        try {
            taskList.deleteTask(2);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(1, taskList.getNumberOfTasks());
    }

    @Test
    public void changeTaskStatus_invalidIndex_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            taskList.changeTaskStatus(-1, true);
        });

        assertEquals("You must specify which task to mark or unmark!", de.getMessage());
    }

    @Test
    public void changeTaskStatus_changesTaskStatus_correct() {
        Task t = null;
        try {
            t = taskList.changeTaskStatus(1, true);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        assertEquals("X", t.getDoneStatus());
    }

    @Test
    public void findMatchingTasks_keywordToFilter_findsCorrectTasks() {
        ArrayList<Task> temp = taskList.findMatchingTasks("first");

        assertEquals(todo1, temp.get(0));
    }

}
