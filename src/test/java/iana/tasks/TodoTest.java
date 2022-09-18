package iana.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    public void toStringTest() {
        Todo todo = new Todo("Leetcode grind hard", false);
        assertEquals("[T][] Leetcode grind hard", todo.toString());
    }
}
