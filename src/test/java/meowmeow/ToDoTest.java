package meowmeow;
import meowmeow.events.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void todoSaveData(){
        ToDo t = new ToDo("Buy milk");
        String expected = "T | false | Buy milk";
        String actual = t.getSaveData();
        assertEquals(expected, actual);
    }

    @Test
    public void todoToString(){
        ToDo t = new ToDo("Buy milk");
        String expected = "[T] [ ] Buy milk";
        String actual = t.toString();
        assertEquals(expected, actual);
    }
}
