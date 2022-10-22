package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ToDosTest {
    @Test
    public void ValidARGs() {
        ToDos dummyToDo = new ToDos("sample");
        String expectedOutcome = "sample";
        String testOutcome = dummyToDo.getToDoDescirption();
        assertEquals(testOutcome,expectedOutcome);
    }
    @Test
    public void invalidValidARGs() {
        ToDos dummyToDo = new ToDos("sample");
        String expectedOutcome = "Won't Work";
        String testOutcome = dummyToDo.getToDoDescirption();
        assertNotEquals(testOutcome,expectedOutcome);
    }
}
