package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTaskTest {

    @Test
    public void toStringTest() {
        String actual = new EventTask("Meeting", "16092022", "1500").toString();
        String expected = "[E][0] Meeting | 16 Sep 2022 1500";
        assertEquals(expected, actual);
    }

}
