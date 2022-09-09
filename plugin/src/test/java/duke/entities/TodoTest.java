
package duke.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class TodoTest {
    @Test
    public void todoInitTest() throws DukeException {
        String des = "Testing...";
        Todo td = new Todo(des);
        String cur = td.toString();
        String target = "[T][ ] Testing...";
        assertEquals(cur, target);
    }

    @Test
    public void todoMarkTest() throws DukeException {
        String des = "Testing...";
        Todo td = new Todo(des);
        td.toggleComplete();
        String cur = td.toString();
        String target = "[T][X] Testing...";
        assertEquals(cur, target);
    }

}