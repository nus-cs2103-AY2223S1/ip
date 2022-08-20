package ren;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TimeStampTest {
    @Test
    public void of_dateTime_success() {
        try {
            TimeStamp test = TimeStamp.of("20/8/2022-22:04");
            assertEquals(" Sat, 20 August 2022 10:04 PM", test.toString());
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void of_invalidDateTime_exceptionThrown() {
        try {
            TimeStamp.of("lmao");
            fail();
        } catch (RenException e) {
            assertEquals("Please indicate date and time properly. (20/8/2022-15:37)", e.getMessage());
        }
    }

    @Test
    public void fromFile_dateTime_success() {
        TimeStamp test = TimeStamp.fromFile("Sat, 20 August 2022 10:04 PM");
        assertEquals(" Sat, 20 August 2022 10:04 PM", test.toString());
    }
}
