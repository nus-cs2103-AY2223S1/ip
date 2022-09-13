package john.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import john.data.TaskList;

public class UiTest {
    @Test
    public void showGreetingTest() {
        assertEquals("Hello, I'm John. What can I do for you today?", new Ui().showGreeting());
    }

    @Test
    public void showGoodbyeTest() {
        assertEquals("Goodbye!", new Ui().showGoodbye());
    }

    @Test
    public void showAddedTaskTest() {
        String sep = System.lineSeparator();
        assertEquals("I've added this task!" + sep + "task" + sep + "You have 0 tasks in your list.",
                new Ui().showAddedTask("task", new TaskList()));
    }

    @Test
    public void showDeletedTaskTest() {
        String sep = System.lineSeparator();
        assertEquals("I've deleted this task!" + sep + "task" + sep + "You have 0 tasks in your list.",
                new Ui().showDeletedTask("task", new TaskList()));
    }

    @Test
    public void showUnmarkedTaskTest() {
        String sep = System.lineSeparator();
        assertEquals("I've unmarked this task!" + sep + "task",
                new Ui().showUnmarkedTask("task"));
    }

    @Test
    public void showMarkedTaskTest() {
        String sep = System.lineSeparator();
        assertEquals("I've marked this task as complete!" + sep + "task",
                new Ui().showMarkedTask("task"));
    }

    @Test
    public void showInvalidTaskNumberTest() {
        String sep = System.lineSeparator();
        assertEquals("This is an invalid task number." + sep + "You have 0 tasks in your list.",
                new Ui().showInvalidTaskNumber(new TaskList()));
    }

    @Test
    public void showTasks_noInputTasks_noTasksInListReturned() {
        assertEquals("There are no tasks in your list.",
                new Ui().showTasks(new TaskList(), "12/12/2022"));
    }

    @Test
    public void showTasks_noFoundTasks_noTasksFoundReturned() {
        TaskList taskList = new TaskList();
        taskList.addTodo("hello");
        assertEquals("There are no tasks found for '12/12/2022'.",
                new Ui().showTasks(taskList, "12/12/2022"));
    }

    @Test
    public void showTasks_inputTasks_tasksReturned() {
        String sep = System.lineSeparator();
        assertEquals("1. hello" + sep + "2. world" + sep,
                new Ui().showTasks(new TaskList(), "12/12/2022", "hello", "world"));
    }

    @Test
    public void showIncorrectCommandTest() {
        assertEquals("I cannot understand 'hello'. Try another command!",
                new Ui().showIncorrectCommand("hello"));
    }

    @Test
    public void showIncorrectCommandWithFormatTest() {
        String sep = System.lineSeparator();
        assertEquals("This is an invalid HELLO format." + sep + "The correct format is 'world'.",
                new Ui().showIncorrectCommandWithFormat("hello", "world"));
    }

    @Test
    public void showHelpTest() {
        String sep = System.lineSeparator();
        assertEquals("Here's the list of commands that I know! The commands are not case-sensitive."
                + sep + sep + "Creating Tasks" + sep + "1. todo <description>: Add a todo task" + sep
                + "2. deadline <description> /by <dd/mm/yyyy> <hhmm | optional>: Add a deadline task" + sep
                + "3. event <description> /at <dd/mm/yyyy> <hhmm | optional>: Add a event task" + sep + sep
                + "Editing Tasks" + sep + "1. mark <positive integer>: Mark task as done" + sep
                + "2. unmark <positive integer>: Unmark task" + sep + sep
                + "Deleting Tasks" + sep + "1. delete <positive integer>: Delete specified task" + sep + sep
                + "Showing Tasks" + sep + "1. list <dd/mm/yyyy | optional>: Lists tasks (on a specific date)" + sep
                + "2. find <keyword>: Find tasks by keyword" + sep + sep
                + "Exiting" + sep + "1. bye: Exiting the application",
                new Ui().showHelp());
    }
}
