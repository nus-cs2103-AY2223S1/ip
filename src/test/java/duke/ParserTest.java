package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

	@Test
	public void emptyListTest() {
		try {
			Parser parser = new Parser(new Storage("data/empty.txt"), new TaskList());
			parser.reply("list");
			fail("No exception thrown");
		} catch (DukeException e) {
			assertEquals("there's nothing!", e.getMessage());
		} catch (IOException e) {
			fail("Wrong exception thrown");
		}
	}

	@Test
	public void deleteTest() {
		try {
			Parser parser = new Parser(new Storage("data/empty.txt"), new TaskList());
			parser.reply("delete me");
			fail("No exception thrown");
		} catch (DukeException e) {
			assertEquals("I don't know which to delete!", e.getMessage());
		} catch (IOException e) {
			fail("Wrong exception thrown");
		}
	}

	@Test
	public void invalidResponseTest() {
		try {
			Parser parser = new Parser(new Storage("data/empty.txt"), new TaskList());
			parser.reply("what");
			fail("No exception thrown");
		} catch (DukeException e) {
			assertEquals("I'm sorry, but I don't know what that means.", e.getMessage());
		} catch (IOException e) {
			fail("Wrong exception thrown");
		}
	}
}
