package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void todoTest() {
        assertEquals(1, new Todo("drink", true).getDone());
        assertEquals("drink", new Todo("drink", true).getTask());
    }
}