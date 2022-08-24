import Duke.Ui;
import Duke.task.TaskTodo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void lineDividerTest() {
        Ui ui = new Ui();
        System.setOut(new PrintStream(outputStream));
        ui.lineDivider();
        assertEquals("-".repeat(60) + "\n", outputStream.toString());
    }

    @Test
    public void endPromptTest() {
        Ui ui = new Ui();
        System.setOut(new PrintStream(outputStream));
        ui.endPrompt();
        assertEquals("Goodbye" + "\n", outputStream.toString());
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