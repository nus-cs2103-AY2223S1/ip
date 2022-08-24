package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void cleanUp() {
        System.setOut(stdout);
    }

    @Test
    public void welcomeMessageTest() {
        Ui.printWelcomeMessage();
        String expected = "Hello from Botto\nWhat can I do for you?";

        assertEquals(outputStream.toString().trim(), expected);
    }

    @Test
    public void goodbyeMessageTest() {
        Ui.printGoodbyeMessage();
        String expected = "Bye. Hope to see you again soon!";

        assertEquals(outputStream.toString().trim(), expected);
    }
}
