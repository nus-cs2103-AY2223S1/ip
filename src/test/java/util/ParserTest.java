package util;

import alanExceptions.AlanException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void validInput1() throws AlanException {
        assertEquals("by",
                parser.parse(InputType.deadline, "buy things /by tonight").
                        getDuring());
    }

    @Test
    public void validInput2() throws AlanException {
        assertEquals("03/11/2023 12:13",
                parser.parse(InputType.deadline, "throw stuff /by 03/11/2023 12:13").
                        getTimeText());
    }
    @Test
    public void validInput3() throws AlanException {
        assertEquals("do hw",
                parser.parse(InputType.todo, " do hw").
                        getTaskName());
    }
    @Test
    public void invalidNoValueException() {
        try {
            parser.parse(InputType.mark, "mark");
            fail("NoValueException was not thrown");
        } catch (AlanException e) {
            assertEquals("mark must be followed by a value, please enter a value.",
                    e.getMessage());
        }
    }
}
