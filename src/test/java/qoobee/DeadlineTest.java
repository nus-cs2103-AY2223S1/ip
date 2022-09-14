package qoobee;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toStringTest() {
        assertEquals("[D][ ] hand in assignment (by: Sep 15 2022 12:00 PM)",
                new Deadline("hand in assignment", "2022-09-15 12:00").toString());
    }

    @Test
    public void toStorageStringTest() {
        assertEquals("D | 0 | hand in assignment | 2022-09-15 12:00 | NA\n",
                new Deadline("hand in assignment", "2022-09-15 12:00").storageToString());
    }

}
