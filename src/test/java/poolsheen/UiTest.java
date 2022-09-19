package poolsheen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class UiTest {
    private static final String HORIZONTAL_LINE = "--------------------------------------------------";

    private static final String LAST_REPLY = "meow *_*";

    @Test
    public void newLine_properLine_success() {
        Ui ui = new Ui();
        assertEquals("testing\n", ui.newLine("testing"));
    }

    @Test
    public void readCommand_noLine_exceptionThrown() {
        Ui ui = new Ui();
        try {
            assertEquals(" ", ui.readCommand());
        } catch (NoSuchElementException e) {
            assertEquals("No line found", e.getMessage());
        }
    }

    @Test
    public void say_hello_success() {
        Ui ui = new Ui();
        String expectedStr = HORIZONTAL_LINE + "\n"
                + "hello\n"
                + LAST_REPLY + "\n" + HORIZONTAL_LINE;
        assertEquals(expectedStr, ui.say("hello"));
    }
}
