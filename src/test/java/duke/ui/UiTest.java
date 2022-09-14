package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.data.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;

public class UiTest {
    @Test
    public void showTaskAdded_oneTask_success() {
        Ui ui = new Ui();
        Todo todo = new Todo("run");
        assertEquals("Sure! Task added:\n  [T][ ] run", ui.showTaskAdded(todo));
    }

    @Test
    public void showTaskRemoved_oneTask_success() {
        Ui ui = new Ui();
        Todo todo = new Todo("run");
        assertEquals("Alright! Task removed:\n  [T][ ] run", ui.showTaskRemoved(todo));
    }

    @Test
    public void showTaskDone_oneDoneTask_success() {
        Ui ui = new Ui();
        Todo todo = new Todo("run");
        todo.changeStatus(true);
        assertEquals("Speedy, keep it up!\n\nTask marked as done:\n  [T][X] run", ui.showTaskDone(todo));
    }

    @Test
    public void showTaskNotDone_oneNotDoneTask_success() {
        Ui ui = new Ui();
        Todo todo = new Todo("run");
        todo.changeStatus(false);
        assertEquals("Welp, don't fall behind!\n\nTask marked as not done:\n  [T][ ] run", ui.showTaskNotDone(todo));
    }

    @Test
    public void showNumberOfTasks_twoTasks_success() {
        Ui ui = new Ui();
        assertEquals("\n\nYou have 2 task(s) in the list.", ui.showNumberOfTasks(2));
    }

    @Test
    public void showAllTasks_twoTasks_success() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("sleep");
        taskList.addTask(todo);
        Deadline deadline = new Deadline("do iP", "2022-07-14");
        taskList.addTask(deadline);
        String expected = "Here's your task list:\n1.[T][ ] sleep\n2.[D][ ] do iP (by: 14 Jul 2022)\n";
        assertEquals(expected, ui.showAllTasks(taskList));
    }

    @Test
    public void showAllTasks_emptyTaskList_success() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        assertEquals("Your task list is empty!", ui.showAllTasks(taskList));
    }

    @Test
    public void showUndo_twoTasks_success() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        Todo todo = new Todo("sleep");
        taskList.addTask(todo);
        String expected = "Easy-peasy! The most recent change to your task list has been undone!\n\n"
                + "Here's your task list:\n1.[T][ ] sleep\n";
        assertEquals(expected, ui.showUndo(taskList));
    }

    @Test
    public void showMatchingTasks_twoMatchingTasks_success() {
        Ui ui = new Ui();
        List<Task> matchingTasks = new ArrayList<Task>();
        Todo todo = new Todo("eat");
        matchingTasks.add(todo);
        Deadline deadline = new Deadline("eat even more", "2022-07-14");
        matchingTasks.add(deadline);
        String expected = "Aha, found them!\n\nHere are your matching task(s):\n"
                + "1.[T][ ] eat\n2.[D][ ] eat even more (by: 14 Jul 2022)\n";
        assertEquals(expected, ui.showMatchingTasks(matchingTasks));
    }

    @Test
    public void showMatchingTasks_noMatchingTasks_success() {
        Ui ui = new Ui();
        List<Task> matchingTasks = new ArrayList<>();
        assertEquals("Hmm... there are no matching tasks in your list!", ui.showMatchingTasks(matchingTasks));
    }
}
