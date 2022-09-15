import duke.task.TaskTodo;
import duke.ui.NekoResponses;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NekoResponsesTest {

    @Test
    public void showHelpTest() {
        NekoResponses neko = new NekoResponses();
        assertEquals("What can I do for you?\n" +
                "- todo (task name) \n" +
                "- deadline (task name) /by (date) \n" +
                "- event (task name) /at (date) \n" +
                "- list\n" +
                "- find (value)\n" +
                "- check (index)\n" +
                "- uncheck (index)\n" +
                "- delete (index)\n" +
                "- help \n" +
                "- bye \n" +
                "\n" +
                "EXAMPLES: \n" +
                "todo cut hair \n" +
                "deadline cut hair /by 2022-09-11 \n" +
                "event cut hair /at 2022-09-11 \n" +
                "list\n" +
                "find cut\n" +
                "check 1\n" +
                "uncheck 1\n" +
                "delete 1\n" +
                "help \n" +
                "bye \n", neko.showHelp());
    }

    @Test
    public void endPromptTest() {
        NekoResponses neko = new NekoResponses();
        assertEquals("Goodbye", neko.endPrompt());
    }

    @Test
    public void markDoneTest() {
        TaskTodo taskTodo = new TaskTodo("Test");
        taskTodo.markDone();
        assertEquals(taskTodo.toStorageString(),
                new TaskTodo("Test", true).toStorageString());
    }

    @Test
    public void markUnDoneTest() {
        TaskTodo taskTodo = new TaskTodo("Test", true);
        taskTodo.markUndone();
        assertEquals(taskTodo.toStorageString(),
                new TaskTodo("Test").toStorageString());
    }
}