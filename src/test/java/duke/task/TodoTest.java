package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStorage_notDone() {
        assertEquals("T|0|foo bar", new Todo("foo bar").toStorage());
    }

    @Test
    public void toStorage_done() {
        Todo t = new Todo("foo bar baz");
        t.markAsDone();
        assertEquals("T|1|foo bar baz", t.toStorage());
    }
}
