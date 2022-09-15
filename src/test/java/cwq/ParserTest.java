package cwq;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import cwq.parser.*;
import java.util.*;

public class ParserTest {
    @Test
    public void parserContentTest() {
        Parser parser = new Parser();
        String inputText = "add /p ToDo /p CS2103T";
        String content = null;
        try {
            content = parser.parseContent(inputText);
        } catch (Exception e) {
            content = "";
        }
        assertEquals(content, "CS2103T");
    }

    @Test
    public void parseTaskTest() {
        Parser parser = new Parser();
        String inputText = "add /p Event /p CS2101 OP1 /p 2022-09-12 12:00";
        String task = null;
        try {
            task = parser.parseTask(inputText);
        } catch (Exception e) {
            task = "";
        }
        assertEquals(task, "Event");
    }

    @Test
    public void parseTimeTest() {
        Parser parser = new Parser();
        String inputText = "add /p Deadline /p CS2309S Assigment2 /p 2022-09-11 23:59";
        String time = null;
        try {
            time = parser.parseTime(inputText);
        } catch (Exception e) {
            time = "";
        }
        assertEquals(time, "2022-09-11 23:59");
    }
}