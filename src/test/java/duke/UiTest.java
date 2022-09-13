package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void helloTest() throws DukeException {
        String expectedString = "Hello! I'm Duke! What can I do for you?";

        assertEquals(new Ui().greet(), expectedString);
    }

    @Test
    public void byeTest() throws DukeException {
        String expectedString = "Bye. Hope to see you again soon ^^!";

        assertEquals(new Ui().bye(), expectedString);
    }
}
