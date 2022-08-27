package ploopy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        Deadline deadline = new Deadline("finish 2103T work", "27/08/2022 1600");
        assertEquals("[D][ ] finish 2103T work (by: Aug 27 2022 1600)", deadline.toString());
    }
}
