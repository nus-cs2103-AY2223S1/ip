package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        Deadline d = new Deadline("deadline return book ", "2/12/2019 1800");
        assertEquals("[D][ ] deadline return book (by: Dec 2 2019 1800)", d.toString());
    }

}
