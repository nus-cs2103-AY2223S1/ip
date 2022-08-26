package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_addAnEvent_success() {
        Parser parser = new Parser();
        TaskList tl = new TaskList();

        parser.parse("event attend wedding /at 2022-10-28 18:00", tl);
        String expected = "E | O | attend wedding | Oct 28 2022 | 18:00";
        assertEquals(expected, tl.getTasks().get(0).toString());
    }

    @Test
    public void parse_markTaskAsDone_success() {
        Parser parser = new Parser();
        TaskList tl = new TaskList();

        Deadline deadlineTask = new Deadline("return books", "2022-07-12 07:00");

        tl.addTask(deadlineTask);
        parser.parse("mark 1", tl);
        assertEquals("X", tl.getTasks().get(0).getStatusIcon());
    }
}
