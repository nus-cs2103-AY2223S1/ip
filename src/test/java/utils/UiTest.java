package utils;

import duke.Duke;
import exceptions.DukeException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
	@Test
	public void testDisplayErrorMessage() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		final PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outContent));

		Ui.printErrorWithoutFormatting(new DukeException("Test"));
		assertEquals("Test", outContent.toString());
		System.setOut(originalOut);
	}
}
