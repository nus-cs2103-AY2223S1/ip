package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import exception.DorisException;
import task.Deadline;
import task.Task;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTestOne() throws DorisException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        Task expected = new Deadline("task", LocalDateTime.parse("2002-02-02 12:30 PM", df));
        assertEquals(Parser.parseSaved("D | task | false | 02 Feb 2002 12:30 PM").toStringText(),
                expected.toStringText());
    }

    @Test
    public void parserTestTwo() throws DorisException {
        Task expected = new Todo("task");
        assertEquals(Parser.parseSaved("T | task | false").toStringText(), expected.toStringText());
    }
}
