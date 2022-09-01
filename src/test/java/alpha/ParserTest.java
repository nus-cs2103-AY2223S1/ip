package alpha;

import alpha.command.Add;
import alpha.command.Delete;
import alpha.command.Mark;
import alpha.command.Unmark;
import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void interpretMessage_inputMessage_command() throws AlphaException {
        assertEquals(new Add(new Todo("read", "T")), parser.interpretMessage("todo read"));
        assertEquals(new Add(new Event("festival", "Dec 12 2020", "E")), parser.interpretMessage("event festival /on 2020-12-12"));
        assertEquals(new Add(new Deadline("read", "Dec 12 2020", "D")), parser.interpretMessage("deadline read /by 2020-12-12"));
        assertEquals(new Mark(1), parser.interpretMessage("mark 1"));
        assertEquals(new Unmark(5), parser.interpretMessage("unmark 5"));
        assertEquals(new Delete(2), parser.interpretMessage("delete 2"));
    }

    @Test
    public void interpretMessage_inputMessage_exception() {
        try {
            parser.interpretMessage("event");
        } catch (AlphaException e){
            assertEquals(new AlphaException("Invalid input: Task description is missing!"), e);
        }
        try {
            parser.interpretMessage("read /by 2020-12-12");
        } catch (AlphaException e){
            assertEquals(new AlphaException("Invalid input: Task type unknown!"), e);
        }
        try {
            parser.interpretMessage("event xyz /on 22");
        } catch (AlphaException e){
            assertEquals(new AlphaException("Invalid input: Input date must be in YYYY-MM-DD format!"), e);
        }
        try {
            parser.interpretMessage("deadline xyz /on 22");
        } catch (AlphaException e){
            assertEquals(new AlphaException("Invalid input: Incorrect format! Enter help to learn about the command formats."), e);
        }
    }
}
