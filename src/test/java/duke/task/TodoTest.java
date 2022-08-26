package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    void todoTest() {
        Todo test = new Todo("test");

        assertEquals("[T][ ] test", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[T][X] test", test.toString(), "markAsDone() method works");
    }
}
