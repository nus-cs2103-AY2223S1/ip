package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    
    @Test
    void numberOfTasks_empty() {
        Ui ui = new Ui();
        String output = ui.numberOfTasks(0);
        assertEquals("Now you have 0 task in your list.", output);
    }
    
    @Test
    void printTask_emptyList() {
        Ui ui = new Ui();
        System.setOut(new PrintStream(outputStream));
        ui.printTasks(new ArrayList<>());
        assertEquals("Here are the tasks in your list: \r\n", outputStream.toString());
    }
}
