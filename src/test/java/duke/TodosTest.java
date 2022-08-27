package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest {

    @Test
    public void toStringTest() {
        ToDos test = new ToDos("checking");
        assertEquals("[T][ ] checking",test.toString());
    }
}
