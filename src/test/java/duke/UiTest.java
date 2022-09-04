package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));     // capture what is supposed to be sent to System.out
    }

    @Test
    public void showLoadingErrorTest() {
        Ui ui = new Cli();
        ui.showLoadingError();
        assertEquals(
                "------------------------------\nThere was a problem loading the tasks from the output file. " +
                        "Starting with empty list.\n------------------------------\n\n",
                outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }
}
