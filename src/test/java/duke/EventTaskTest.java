package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTaskTest {

	@Test
	public void EventTaskTest() throws DukeException {
		EventTask eventTask = new EventTask("work ", "2022-12-20 1800");
		assertEquals("[E][ ] work  (at: Dec 20 2022 6:00 PM)", eventTask.toString());
	}
}
