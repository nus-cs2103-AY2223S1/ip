package Models;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void creationTest(){
        Todo t = new Todo("aTitle", false);
        assertEquals("aTitle", t.getTitle());
    }

    @Test
    public void stringTest(){
        Todo t = new Todo("aTitle", false);
        assertEquals("[T] [ ] aTitle", t.toString());
    }
}
