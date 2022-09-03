package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    void todoTest() {
        Todo test = new Todo("test");

        assertEquals("[T][ ] test", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[T][X] test", test.toString(), "markAsDone() method works");
    }
}
