package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

	@Test
	public void TodoTaskTest(){
		TodoTask todoTask = new TodoTask("work");
		assertEquals("[T][ ] work", todoTask.toString());
	}
}
