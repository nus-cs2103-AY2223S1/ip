package hazell;

import hazell.exceptions.KwargNotFound;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {
    @Test
    public void test1() {
        Command parsed = Command.parse("todo borrow book");
        assertTrue(parsed.startsWith("todo"));
        assertEquals(parsed.getTrailingArgs(), List.of("borrow", "book"));
    }

    @Test
    public void test2() throws KwargNotFound {
        Command parsed = Command.parse("deadline return book /by Sunday");
        assertEquals(parsed.getKwarg("by"), "Sunday");
    }

//    @Test
//    public void test3() {
//        System.out.println(Command.parse("event project meeting /at Mon 2-4pm"));
//    }
}

