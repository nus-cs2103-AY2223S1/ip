package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStore_correctInputs_correctStringReturned() {
        assertEquals("T : 0 : finish iP",
                new Todo("finish iP").toStore());
    }
}
