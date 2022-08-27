package duke;

import duke.dukeExceptions.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Parser parser;
    private TaskList taskList;
    
    @BeforeEach
    public void setUp() {
        this.parser = new Parser();
        this.taskList = new TaskList();
    }
    
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }
    
    @Test
    public void parse_emptyInput_returnsInvalid() throws DukeException {
        String[] emptyInputs = { "", " ", "\n \n"};
        String returnMsg = "I don't know that command please enter a valid command";
        
        for (String input : emptyInputs) {
            assertEquals(this.parser.parseInput(input, this.taskList), returnMsg);
        }
    }
    
    @Test
    public void parse_invalidInput_returnsInvalid() throws DukeException {
        String[] invalidInputs = { "lis", "del", "task" };
        String returnMsg = "I don't know that command please enter a valid command";

        for (String input : invalidInputs) {
            assertEquals(this.parser.parseInput(input, this.taskList), returnMsg);
        }
    }
}
