import ui.Ui;
import exceptions.NoTasksException;
import objects.Task;
import objects.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void showTasksWithNoTasksException() {
        try {
            List<Task> tasks = new ArrayList<>();
            Ui.showTasks(tasks);
            fail("Test Fail: NoTasksException is not thrown.");
        } catch (NoTasksException e) {
            assertNotNull(e);
        }
    }

    @AfterEach
    public void clearDown() {
        System.setOut(standardOut);
    }
}
