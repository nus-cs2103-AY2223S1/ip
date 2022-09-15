package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        LocalDateTime dateTime = LocalDateTime.parse("2022-09-15T18:08");
        Deadline d = new Deadline("dance", dateTime);
        assertEquals("[D][ ] dance (by: 15 Sep 2022 6:08pm)", d.toString());
    }

    @Test
    public void testEqual() {
        LocalDateTime firstDateTime = LocalDateTime.parse("2022-09-15T17:08");
        LocalDateTime firstDateTimeCopy = LocalDateTime.parse("2022-09-15T17:08");
        LocalDateTime secondDateTime = LocalDateTime.parse("2022-08-15T17:08");
        Deadline firstDeadline = new Deadline("dance", firstDateTime);
        Deadline firstDeadlineCopy = new Deadline("dance", firstDateTimeCopy);
        Deadline secondDeadline = new Deadline("sing", firstDateTime);
        Deadline thirdDeadline = new Deadline("dance", secondDateTime);
        assertFalse(firstDeadline.equals(secondDeadline));
        assertFalse(firstDeadline.equals(thirdDeadline));
        assertTrue(firstDeadline.equals(firstDeadlineCopy));
    }

}
