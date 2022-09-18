package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToStringMethod() {
        Todo t = new Todo("housework");
        assertEquals("[T][ ] housework", t.toString());
    }

}
