package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TasksTest {
    @Test
    public void testDelete() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("delete 1"),
                "Noted. I have removed this task: \n" + "[T][ ] read book");
    }
}
