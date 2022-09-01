package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor)); // capture what is supposed to be sent to System.out
    }

    @Test
    public void showLoadingErrorTest() {
        Ui ui = new Ui();
        ui.showLoadingError();
        assertEquals(
                "------------------------------\nThere was a problem loading the tasks from the output file. "
                        + "Starting with empty list.\n------------------------------\n\n",
                outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }
}
