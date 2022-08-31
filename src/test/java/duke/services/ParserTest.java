package duke.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class ParserTest {
    @Test
    public void testGetTaskNumber() {
        //exception on trying to get task number when there are no tasks
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark", "1"}));
        TaskList.addTask(new Todo(""));
        assertEquals(Parser.getTaskNumber(new String[] {"mark", "1"}), 1);
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark"}));
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark", "2a"}));
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark", "9"}));
    }

    @Test
    public void testGetDescriptionAndDate() {
        String[] event1 = new String[] {"event", "presentation", "/at", "9/10/2022"};
        assertEquals(Parser.getDescription(event1, "/at"), "presentation");
        assertEquals(Parser.getTiming(event1, "/at"), "9/10/2022");
        String[] deadline1 = new String[] {"deadline", "finish", "", "", "essay", "/by", "tmr", "night",
            "", "", "", "or", "ask", "4", "extension"};
        assertEquals(Parser.getDescription(deadline1, "/by"), "finish   essay");
        assertEquals(Parser.getTiming(deadline1, "/by"), "tmr night    or ask 4 extension");
        //does it throw exception for missing all other arguments?
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getDescription(new String[] {"event"}, "/at"));
        //exception on timing flag included but missing desc?
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getDescription(new String[] {"deadline", "/by"}, "/by"));
        //exception on missing flag?
        assertThrows(IllegalArgumentException.class, () -> {
            String[] deadline = new String[]{"deadline", "help", "by"};
            Parser.getDescription(deadline, "/by");
            Parser.getTiming(deadline, "/by");
        });
        //exception on missing timing?
        assertThrows(IllegalArgumentException.class, () -> {
            String[] deadline = new String[]{"deadline", "buy", "food", "/by"};
            Parser.getDescription(deadline, "/by");
            Parser.getTiming(deadline, "/by");
        });
        //exception on wrong flag?
        assertThrows(IllegalArgumentException.class, () -> {
            String[] deadline = new String[]{"deadline", "buy", "food", "/at", "tmr"};
            Parser.getDescription(deadline, "/by");
            Parser.getTiming(deadline, "/by");
        });
    }
}
