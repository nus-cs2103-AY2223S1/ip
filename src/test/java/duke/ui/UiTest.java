package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private final Ui ui = new Ui();
    // code copied from https://www.baeldung.com/java-testing-system-out-println
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
    public void printWelcomeMessage_getCorrectOutput() {
        ui.printWelcomeMessage();
        String expectedOutcome = "----------------------------------------\n"
                + "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "\nWait, I'm not Duke\n"
                + "I'm Yem, your personal assistant\n"
                + "What can I do for you master?\n"
                + "----------------------------------------";
        assertEquals(outputStreamCaptor.toString().trim(), expectedOutcome);
    }

    @Test
    public void printFarewellMessage_correctOutput() {
        ui.printFarewellMessage();
        String expectedOutcome = "----------------------------------------\n"
                + "Bye. See you later master!\n"
                + "----------------------------------------";
        assertEquals(outputStreamCaptor.toString().trim(), expectedOutcome);
    }
}
