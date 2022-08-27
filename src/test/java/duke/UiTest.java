package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void dukePrintTest() {
        Ui.dukePrint("hello");
        assertEquals("____________________________________________________________\n" +
                "     hello\n" +
                "     ____________________________________________________________",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void showWelcomeTest() {
        new Ui().showWelcome();
        assertEquals("____________________________________________________________\n" +
                        "     Oi! I'm Dook\n" +
                        "     What's up?\n" +
                        "     Please type your date and time in this format: yyyy-mm-dd\n" +
                        "     ____________________________________________________________",
                outputStreamCaptor.toString().trim());
    }
}
