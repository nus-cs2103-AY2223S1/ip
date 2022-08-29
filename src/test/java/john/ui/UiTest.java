package john.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    // Adapted from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void showGreetTest() {
        Ui ui = new Ui();
        ui.showGreeting();
        assertEquals("|  Welcome to JDuke -- Version 1.0\n|  What can I do for you?",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void showGoodbyeTest() {
        Ui ui = new Ui();
        ui.showGoodbye();
        assertEquals("|  Goodbye", outputStreamCaptor.toString().trim());
    }

    @Test
    public void showErrorTest() {
        Ui ui = new Ui();
        ui.showErrorMessage("hello error");
        assertEquals("|  Error:\n|  hello error", outputStreamCaptor.toString().trim());
    }

    @Test
    public void showToUserTest() {
        Ui ui = new Ui();
        ui.showToUser("hello", "world");
        assertEquals("helloworld", outputStreamCaptor.toString().trim());
    }

    @Test
    public void showToUserWithIndentTest() {
        Ui ui = new Ui();
        ui.showToUserWithIndent("hello", "world");
        assertEquals("|  hello\n|  world", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
