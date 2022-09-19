
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import tasks.Todos;


public class TodosTest {
    @Test
    public void addTest() {
        Todos todo = new Todos("Do one");
        assertEquals("[T][ ] Do one", todo.toString());
    }

    @Test
    public void markTest() {
        Todos todo = new Todos("Do one");
        todo.mark();
        assertEquals("[T][X] Do one", todo.toString());
    }

    @Test
    public void unmarkTest() {
        Todos todo = new Todos("Do one");
        todo.mark();
        todo.unmark();
        assertEquals("[T][ ] Do one", todo.toString());
    }
}
