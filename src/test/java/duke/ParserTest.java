package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void checkDeadlineInputTest(){
        Parser parser = new Parser();
        String[] arg = {"deadline", "description" ,"/by", "duration"};
        try {
            int i = parser.checkDeadlineInput(arg);
            assertEquals(2,i);
        } catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    public void checkEventInputTest(){
        Parser parser = new Parser();
        String[] arg = {"event", "description", "another", "word" ,"/at", "duration"};
        try {
            int i = parser.checkEventInput(arg);
            assertEquals(4, i);
        } catch (DukeException e) {
            Assertions.fail();
        }
    }
}
