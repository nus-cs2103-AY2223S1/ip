package parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import tasks.*;
import exception.LunaException;

public class ParserTest {
    @Test
    public void parserTestOne(){
        Task expected = new Deadline("task", "2002-02-02");
        assertEquals(Parser.parseSaved("       [D][ ] task BY 02 Feb 2002").toString(), expected.toString());
    }

    @Test
    public void parserTestTwo(){
        assertEquals(Parser.parseSaved("  Luna finds the following items saved in your list"), null);
    }

    @Test
    public void parserTestThree(){
        Task expected = new Todo("task");
        assertEquals(Parser.parseSaved("       [T][ ] task").toString(), expected.toString());
    }
}