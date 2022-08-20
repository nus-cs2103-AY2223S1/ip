package ren.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ren.RenException;

public class DeadlineTest {
    @Test
    public void toString_description_success() {
        try {
            Deadline test = new Deadline("test", "20/8/2022-11:11");
            assertEquals("[D][ ] test(by: Sat, 20 August 2022 11:11 AM)\n", test.toString());
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void writeData_description_success() {
        try {
            Deadline test = new Deadline("test", "20/8/2022-11:11");
            assertEquals("D| |test| Sat, 20 August 2022 11:11 AM", test.writeData());
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void readData_description_success() {
        Deadline test = Deadline.readData(new String[] {"D", " ", "test", "Sat, 20 August 2022 11:11 AM"});
        assert test != null;
        assertEquals("D| |test| Sat, 20 August 2022 11:11 AM", test.writeData());
    }
}
