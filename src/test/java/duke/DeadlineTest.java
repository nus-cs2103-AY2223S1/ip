package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][ ] return book (by: Mar 21 2022)", new Deadline("return book", "2022-03-21").toString());
    }
}
