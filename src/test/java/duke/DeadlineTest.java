package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {

    @Test
    public void toStringTest() {
        Deadline test = new Deadline("Read book","2020-09-09");
        assertEquals("[D][ ] Read book (by: Sep 9 2020)", test.toString());
    }


}
