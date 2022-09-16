package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo temp1 = new Todo("", false , PriorityLevel.Priority.HIGH);

        assertEquals("[T][ ] (Priority: HIGH)", temp1.toString());

        Todo temp2 = new Todo("read book", false, PriorityLevel.Priority.HIGH);

        assertEquals("[T][ ] read book (Priority: HIGH)" , temp2.toString());

        temp2.mark();

        assertEquals("[T][X] read book (Priority: HIGH)", temp2.toString());
    }

}
