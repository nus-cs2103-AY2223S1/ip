package duke.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TasklistTest {
    @Test
    public void addTest() {
        Tasklist list = new Tasklist();
        assertEquals(list.toString(), "[]");
        list.add(new Todo("hello"));
        assertEquals(list.toString(), "[[T][ ] hello]");
    }

    @Test
    public void removeTest() {
        Tasklist list = new Tasklist();
        assertEquals(list.toString(), "[]");
        list.add(new Todo("hello"));
        assertEquals(list.toString(), "[[T][ ] hello]");
        list.remove(0);
        assertEquals(list.toString(), "[]");
    }
}
