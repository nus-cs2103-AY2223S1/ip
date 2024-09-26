package jarvis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jarvis.task.ToDo;

public class ToDoTest {
    private ToDo test = new ToDo("gym");

    @Test
    public void toString_normalInput_displayCorrectly() {
        assertEquals("[T][ ] gym", test.toString());
    }

    @Test
    public void mark_normalInput_markedCorrectly() {
        test.mark();
        assertEquals("[T][X] gym", test.toString());
    }

    @Test
    public void unmark_normalInput_unmarkedCorrectly() {
        test.unmark();
        assertEquals("[T][ ] gym", test.toString());
    }
}
