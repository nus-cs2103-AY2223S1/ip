package poolsheen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

public class UiTest {

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
}
