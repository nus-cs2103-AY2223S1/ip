package dukechatbot.utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The TodoTest class implements test cases to determine
 * the correctness of the Todo class.
 */
public class TodoTest {
    /**
     * tests the correctness of the toString method of the Todo class.
     */
    @Test
    public void todoToStringTest() {
        if ((new Todo("read book").toString()).equals("[T][ ] read book")) {
            assertEquals(1, 1);
        } else {
            assertEquals(0, 1);
        }
    }
}
