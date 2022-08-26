package BetaGo.Tasks;

import BetaGo.Exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void newDeadlineTest(){
        try {
            Deadline temp = new Deadline("do this task", "2012-01-21 6pm");
            assertEquals("[D][ ] do this task (by: Jan 21 2012, 6pm)", temp.toString());
        } catch (InvalidCommandException e) {}
    }
}
