package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTaskTest {

    @Test
    public void toStringTest() {
        String actual = "[T][0] CS2103";
        String expected = (new TodoTask("CS2103")).toString();
        assertEquals(expected, actual);
    }

}
