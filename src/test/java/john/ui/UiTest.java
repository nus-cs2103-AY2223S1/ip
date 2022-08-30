package john.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class UiTest {
    @Test
    public void showGoodbyeTest() {
        assertEquals("Goodbye!", new Ui().showGoodbye());
    }

    @Test
    public void showAddedTaskTest() {
        assertEquals("I've added this task!\ntask\nYou have 0 tasks in your list.",
                new Ui().showAddedTask("task", new TaskList()));
    }

    @Test
    public void showDeletedTaskTest() {
        assertEquals("I've deleted this task!\ntask\nYou have 0 tasks in your list.",
                new Ui().showDeletedTask("task", new TaskList()));
    }

    @Test
    public void showUnmarkedTaskTest() {
        assertEquals("I've unmarked this task!\ntask",
                new Ui().showUnmarkedTask("task"));
    }

    @Test
    public void showMarkedTaskTest() {
        assertEquals("I've marked this task as complete!\ntask",
                new Ui().showMarkedTask("task"));
    }

    @Test
    public void showInvalidTaskNumberTest() {
        assertEquals("This is an invalid task number.\nYou have 0 tasks in your list.",
                new Ui().showInvalidTaskNumber(new TaskList()));
    }

    @Test
    public void showTasksTest() {
        assertEquals("1. hello\n2. world\n", new Ui().showTasks("hello", "world"));
    }

    @Test
    public void showNoTasksTest() {
        assertEquals("There are no tasks in your list.",
                new Ui().showNoTasks(new TaskList(), null));
        TaskList taskList = new TaskList();
        taskList.addTodo("hello");
        assertEquals("There are no tasks found for 'test'.",
                new Ui().showNoTasks(taskList, "test"));
    }

    @Test
    public void showIncorrectCommandTest() {
        assertEquals("I cannot understand 'hello'. Try another command!",
                new Ui().showIncorrectCommand("hello"));
    }

    @Test
    public void showIncorrectCommandWithFormatTest() {
        assertEquals("This is an invalid HELLO format.\nThe correct format is 'world'.",
                new Ui().showIncorrectCommandWithFormat("hello", "world"));
    }

}
