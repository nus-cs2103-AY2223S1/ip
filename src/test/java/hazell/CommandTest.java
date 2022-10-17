package hazell;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import hazell.exceptions.KwargNotFound;


public class CommandTest {
    @Test
    public void test1() {
        Command parsed = Command.parse("todo borrow book");
        assertEquals("todo", parsed.getFirstArg());
        assertEquals(parsed.getTrailingArgs(), List.of("borrow", "book"));
    }

    @Test
    public void test2() throws KwargNotFound {
        Command parsed = Command.parse("deadline return book /by Sunday");
        assertEquals(parsed.getKwarg("by"), "Sunday");
    }
}

