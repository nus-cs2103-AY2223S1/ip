package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void testCheckTypeRequest() throws DukeException {
        assertEquals(Parser.RequestType.DONE, Parser.checkRequest("mark 2"));
        assertEquals(Parser.RequestType.ONGOING, Parser.checkRequest("unmark 2"));
        assertEquals(Parser.RequestType.TODO, Parser.checkRequest("todo borrow a book"));
        assertEquals(Parser.RequestType.EVENT, Parser.checkRequest("event meeting /at 2-4pm"));
        assertEquals(Parser.RequestType.DEADLINE, Parser.checkRequest("deadline return book /by 2022-08-20"));
        assertEquals(Parser.RequestType.DELETE, Parser.checkRequest("delete 2"));
        assertEquals(Parser.RequestType.LIST, Parser.checkRequest("list"));
        assertEquals(Parser.RequestType.EXIT, Parser.checkRequest("bye"));
    }
}
