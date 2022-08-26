package Duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test 
    public void showWelcomeTest() {
        System.setOut(new PrintStream(outputStreamCaptor));
        Ui ui = new Ui();
        String expected = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n"
        + "Hello, welcome to Duke!\n";
        ui.showWelcome();
        assertEquals(expected, outputStreamCaptor.toString());
        System.setOut(standardOut);
    }
}