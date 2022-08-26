package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {

    private static Ui ui;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;
    private PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @BeforeAll
    static void setUp() {
        ui = new Ui();
    }

    @Test
    public void showLineTest() {
        ui.showLine();
        assertEquals(outContent.toString(), "\t____________________________________________\n");
    }

    @Test
    public void showErrorTest() {
        ui.showError("error");
        assertEquals(outContent.toString(), "error\n");
    }
}
