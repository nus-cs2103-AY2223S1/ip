package duke.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

public class ParserTest {
    @Test
    public void getTaskNumber() {
        //exception on trying to get task number when there are no tasks
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark", "1"}));
        TaskList.addTask(new Todo(""));
        assertEquals(Parser.getTaskNumber(new String[] {"mark", "1"}), 1);
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark"}));
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark", "2a"}));
        assertThrows(IllegalArgumentException.class, () -> Parser.getTaskNumber(new String[] {"mark", "9"}));
    }

    @Test
    public void getDescriptionAndTiming_correctEvent() {
        String[] event1 = new String[] {"event", "presentation", "/at", "9/10/2022"};
        assertEquals(Parser.getDescription(event1, "/at"), "presentation");
        assertEquals(Parser.getTiming(event1, "/at"), "9 Oct 2022");
    }

    @Test
    public void getDescriptionAndTiming_correctDeadlineWithEmptySpaces() {
        String[] deadline1 = new String[] {"deadline", "finish", "", "", "work", "/by", "23/4/2021", "4:23pm"};
        assertEquals(Parser.getDescription(deadline1, "/by"), "finish   work");
        assertEquals(Parser.getTiming(deadline1, "/by"), "23 Apr 2021, 4:23PM");
    }

    @Test
    public void getDescription_commandWordOnly_emptyDescriptionException() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getDescription(new String[] {"event"}, "/at"));
    }

    @Test
    public void getDescription_commandWordAndTimingFlagOnly_emptyDescriptionException() {
        assertThrows(IllegalArgumentException.class, () ->
                Parser.getDescription(new String[] {"deadline", "/by"}, "/by"));
    }

    @Test
    public void getDescriptionAndTiming_emptyTimingArgument_emptyTimingException() {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] deadline = new String[]{"deadline", "buy", "food", "/by"};
            Parser.getDescription(deadline, "/by");
            Parser.getTiming(deadline, "/by");
        });
    }

    @Test
    public void getDescriptionAndTiming_wrongFlag_flagNotFoundException() {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] deadline = new String[]{"deadline", "buy", "food", "/at", "2/5/2022"};
            Parser.getDescription(deadline, "/by");
            Parser.getTiming(deadline, "/by");
        });
    }
}
