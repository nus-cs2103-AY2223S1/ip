package bloop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    public void testStringConversion() {
        String task = "2103T Assignment";
        String byInput = "12/08/2022 1530";
        String expected = "[D][ ] 2103T Assignment(by: 12 Aug 2022 03:30 pm)";

        assertEquals(expected, new Deadline(task, byInput).toString());
    }
}