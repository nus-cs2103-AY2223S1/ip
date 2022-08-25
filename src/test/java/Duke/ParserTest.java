package Duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void test_generateToDoFromInput(){
        String description = "eat dinner";
        String input = "todo eat dinner";
        Parser parser = new Parser();
        assertEquals((new ToDo(description)).toString(), (parser.generateToDoFromInput(input)).toString());
    }

    @Test
    public void test_generateDeadlineFromInput(){
        String description = "eat dinner";
        String timeQualifier = "by";
        String timeDescription = "2019-12-27";
        String input = "deadline eat dinner /by 2019-12-27";
        Parser parser = new Parser();
        assertEquals((new Deadline(description, timeQualifier, timeDescription)).toString(), (parser.generateDeadlineFromInput(input)).toString());
    }

    @Test
    public void test_generateEventFromInput(){
        String description = "eat dinner";
        String timeQualifier = "by";
        String timeDescription = "2019-12-27";
        String input = "deadline eat dinner /by 2019-12-27";
        Parser parser = new Parser();
        assertEquals((new Event(description, timeQualifier, timeDescription)).toString(), (parser.generateEventFromInput(input)).toString());
    }
}
