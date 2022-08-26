package Duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void stringTest() {
        ToDo p = new ToDo("sleep");
        assertEquals("[T][ ] sleep",p.toString());
    }

    @Test
    public void testMarkForToDo(){
        ToDo p = new ToDo("sleep");
        p.markAsDone();
        assertEquals(p.toString(), "[T][x] sleep");
    }

    @Test
    public void testUnMarkForToDo(){
        ToDo p = new ToDo("sleep");
        p.markAsDone();
        p.UnmarkAsDone();
        assertEquals(p.toString(), "[T][ ] sleep");
    }
}
