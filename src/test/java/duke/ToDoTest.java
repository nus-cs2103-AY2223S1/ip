package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void newToDoTest() {
        ToDo p = new ToDo("laundry");
        assertEquals("[T][ ] laundry",p.toString());
    }

    @Test
    public void testMarkToDo(){
        ToDo p = new ToDo("laundry");
        p.markAsDone();
        assertEquals(p.toString(), "[T][X] laundry");
    }

    @Test
    public void testUnMarkToDo(){
        ToDo p = new ToDo("laundry");
        p.markAsDone();
        p.markAsUndone();
        assertEquals(p.toString(), "[T][ ] laundry");
    }
}