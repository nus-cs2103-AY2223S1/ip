package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UiTest {
    private final Ui ui = new Ui();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream orignalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restore() {
        System.setOut(orignalOut);
    }

    @ParameterizedTest
    @ValueSource(strings = { "Hello", "Good bye" })
    void formatAndPrint_output(String input) {
        ui.formatAndPrint(input);
        assertTrue(outContent.toString().contains(input));
    }
}
