package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import exceptions.DukeException;

public class UiTest {
    @Test
    public void testDisplayErrorMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Ui.printErrorWithoutFormatting(new DukeException("Test"));
        assertEquals("Test", outContent.toString());
        System.setOut(originalOut);
    }
}
