package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo temp1 = new Todo("", false);

        assertEquals("[T][ ] ", temp1.toString());

        Todo temp2 = new Todo("read book", false);

        assertEquals("[T][ ] read book", temp2.toString());

        temp2.mark();

        assertEquals("[T][X] read book", temp2.toString());
    }

}
