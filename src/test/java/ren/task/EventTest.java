package ren.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ren.RenException;

public class EventTest {
    @Test
    public void toString_description_success() {
        try {
            Event test = new Event("test", "20/8/2022-11:11 ~ 20/8/2022-11:11");
            String expected = "[E][ ] test(at: Sat, 20 August 2022 11:11 AM - Sat, 20 August 2022 11:11 AM)\n";
            assertEquals(expected, test.toString());
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void writeData_description_success() {
        try {
            Event test = new Event("test", "20/8/2022-11:11 ~ 20/8/2022-11:11");
            String expected = "E| |test| Sat, 20 August 2022 11:11 AM| Sat, 20 August 2022 11:11 AM";
            assertEquals(expected, test.writeData());
        } catch (RenException e) {
            fail();
        }
    }

    @Test
    public void readData_description_success() {
        String[] input = {"E", " ", "test", "Sat, 20 August 2022 11:11 AM", "Sat, 20 August 2022 11:11 AM"};
        Event test = Event.readData(input);
        assert test != null;
        String expected = "E| |test| Sat, 20 August 2022 11:11 AM| Sat, 20 August 2022 11:11 AM";
        assertEquals(expected, test.writeData());
    }
}
