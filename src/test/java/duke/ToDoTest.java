package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    void testToString() {
        ToDo unmarkedTest = new ToDo("unmarkedTest");
        ToDo markedTest = new ToDo("markedTest");
        markedTest.toggleStatus();
        String unmarkedTestExpected = "[T] [ ] unmarkedTest";
        String markedTestExpected = "[T] [X] markedTest";
        assertEquals(unmarkedTestExpected, unmarkedTest.toString(), "toString an unmarked todo.");
        assertEquals(markedTestExpected, markedTest.toString(), "toString a marked todo.");
    }

    @Test
    void testToSaveVersion() {
        ToDo unmarkedTest = new ToDo("unmarkedTest");
        ToDo markedTest = new ToDo("markedTest");
        markedTest.toggleStatus();
        String unmarkedTestExpected = "T|0|unmarkedTest\n";
        String markedTestExpected = "T|1|markedTest\n";
        assertEquals(unmarkedTestExpected, unmarkedTest.toSaveVersion(), "toSaveVersion an unmarked todo.");
        assertEquals(markedTestExpected, markedTest.toSaveVersion(), "toSaveVersion a marked todo.");
    }
}
