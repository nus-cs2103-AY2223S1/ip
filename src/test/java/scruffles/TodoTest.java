package scruffles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToString() {
        Todo e = new Todo("drop out");
        assertEquals("[T][ ] drop out", e.toString());
    }
}
