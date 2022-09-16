package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void generateToDoFromInput_parsingSentenceInput_sameOutputAsVariablesInput(){
        String description = "eat dinner";
        String input = "todo eat dinner";
        Parser parser = new Parser();
        assertEquals((new ToDo(description)).toString(), (parser.generateToDoFromInput(input)).toString());
    }

    @Test
    public void generateDeadlineFromInput_parsingSentenceInput_sameOutputAsVariablesInput(){
        String description = "eat dinner";
        String timeQualifier = "by";
        String timeDescription = "2019-12-27";
        String input = "deadline eat dinner /by 2019-12-27";
        Parser parser = new Parser();
        assertEquals((new Deadline(description, timeQualifier, timeDescription)).toString(), (parser.generateDeadlineFromInput(input)).toString());
    }

    @Test
    public void generateEventFromInput_parsingSentenceInput_sameOutputAsVariablesInput(){
        String description = "eat dinner";
        String timeQualifier = "by";
        String timeDescription = "2019-12-27";
        String input = "deadline eat dinner /by 2019-12-27";
        Parser parser = new Parser();
        assertEquals((new Event(description, timeQualifier, timeDescription)).toString(), (parser.generateEventFromInput(input)).toString());
    }
}
