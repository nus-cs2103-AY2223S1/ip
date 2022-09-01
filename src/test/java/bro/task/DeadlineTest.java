package bro.task;

import bro.BroException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void toString_inputString_returnsString() {
        try {
            assertEquals("[D][ ] CS2109S (by: Sep 01 2022 1159)",
                    new Deadline("CS2109S", "01/09/2022 1159").toString());
        } catch (BroException e) {
            fail();
        }
    }
}
