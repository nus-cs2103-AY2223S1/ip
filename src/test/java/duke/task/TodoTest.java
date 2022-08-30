package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.utilities.DukeException;

public class TodoTest {
    private Todo t;

    @Test
    public void todo_emptyDescription_exceptionThrown() {
        DukeException de = assertThrows(DukeException.class, () -> {
            new Todo("");
        });

        assertEquals("The description of a todo cannot be empty!", de.getMessage());
    }

    @Test
    public void toString_stringRepresentation_correct() {
        try {
            t = new Todo("This is a dummy to do task");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("[T][ ] This is a dummy to do task", t.toString());
    }


}
