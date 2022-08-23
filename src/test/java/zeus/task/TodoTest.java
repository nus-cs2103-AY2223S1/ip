package zeus.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todoTest(){
        Todo t = new Todo("test");
        assertEquals("[T][ ] test", t.toString(), "toString() method works");

        t.markAsDone();
        assertEquals("[T][X] test", t.toString(), "markAsDone() method works");

        assertEquals("T | 1 | test", t.getFileFormat(), "getFileFormat() method works");
    }
}
