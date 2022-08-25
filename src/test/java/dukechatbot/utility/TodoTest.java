package dukechatbot.utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoToStringTest() {
        if ((new Todo("read book").toString()).equals("[T][ ] read book")) {
            assertEquals(1, 1);
        } else {
            assertEquals(0, 1);
        }
    }
}
