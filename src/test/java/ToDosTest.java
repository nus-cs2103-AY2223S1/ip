import duke.ToDos;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    ToDos todos = new ToDos("run", 1);

    @Test
    public void testTodos() {
        assertEquals(todos.toString(), "Got it. I've added this task:\n"
                + "[T][ ] " + "run" + "\n" + "Now you have " + 1 + " tasks in the list.");
    }
}
