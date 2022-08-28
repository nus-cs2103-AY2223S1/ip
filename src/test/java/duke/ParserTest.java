package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_deadlineCommand_success() throws DukeException {
        assertEquals(new AddCommand(new Deadline("return book", "2020-01-01")),
                Parser.parse("deadline return book /by 2020-01-01"));
    }
}
