package duke.task;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TodoTest {
    private static Todo todo;

    @BeforeAll
    public static void setUpEvent() {
        todo = new Todo("test description");
    }

    @Test
    public void toFileRepresentation_convert_success() {
        assertEquals("T | 0 | test description",
                todo.toFileRepresentation());
    }

    @Test
    public void fromFileRepresentation_convert_success() {
        Todo result = assertDoesNotThrow(() -> Todo.fromFileRepresentation("T | 0 | test description"));
        assertEquals(todo, result);
    }
}
