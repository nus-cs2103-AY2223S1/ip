package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

	@Test
	public void DeadlineTaskTest() throws DukeException {
		DeadlineTask deadlineTask = new DeadlineTask("work ", "2022-12-20 1800");
		assertEquals("[D][ ] work  (by: Dec 20 2022 6:00 PM)", deadlineTask.toString());
	}
}
