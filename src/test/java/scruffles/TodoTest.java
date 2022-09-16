package scruffles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testToString() {
        Todo e = new Todo("drop out");
        assertEquals("[T][ ] drop out", e.toString());
    }
}
