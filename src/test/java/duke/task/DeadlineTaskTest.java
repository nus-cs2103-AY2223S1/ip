package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTaskTest {

    @Test
    public void toStringTest() {
        String actual = new DeadlineTask("CS2103", "16092022", "2359").toString();
        String expected = "[D][0] CS2103 | 16 Sep 2022 2359";
        assertEquals(expected, actual);
    }

}
