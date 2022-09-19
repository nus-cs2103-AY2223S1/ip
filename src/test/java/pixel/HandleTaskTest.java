package pixel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import pixel.util.*;

public class HandleTaskTest {

    private final String filePath = "./data/pixelTest.txt";
    private final Pixel pixelBot = new Pixel(filePath); // output file address
    private final TaskList taskList = new TaskList(filePath);

    @Test
    public void testInvalidInput() {
        try {
            pixelBot.parserParse("dsfdsfdsfdsf");
        } catch (Exception exception) {
            //System.out.println(exception.toString());
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(), "Input should be a task or a command!");
        }
    }

    @Test
    public void testInvalidDue() {
        try {
            taskList.handleNewTask("deadline /as jjjj", Parser.TaskType.DEADLINE);
        } catch (Exception exception) {
            // System.out.println(exception);
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(),
                "Slash should be followed by \"by\" or \"at\"!");
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            taskList.handleNewTask("deadline CS2103 assignment /by tomorrow", Parser.TaskType.DEADLINE);
            taskList.handleNewTask("todo meet Wayne for dinner /at 2022-06-08 1850", Parser.TaskType.DEADLINE);
            assertEquals(2, Storage.INPUT_TASKS.size());
            Storage.deleteEntry("delete 2", this.filePath);
            assertEquals(1, Storage.INPUT_TASKS.size());
            taskList.handleNewTask("todo meet Wayne for dinner /on 2022-26-08 1850", Parser.TaskType.DEADLINE);
        } catch (Exception exception) {
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals("Slash should be followed by \"by\" or \"at\"!",
                exception.toString());
        }
    }

}
