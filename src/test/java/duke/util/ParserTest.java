package duke.util;

import org.junit.jupiter.api.Test;

import duke.exceptions.CorruptedLineException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseDataFromLine_Invalid1() {
        try {
            DataParser.parseDataFromLine("txt");
            fail();
        } catch (CorruptedLineException e) {
        }
    }

    @Test
    public void parseDataFromLine_Invalid2() {
        try {
            DataParser.parseDataFromLine("Tz <<<< nothing <<<< temperol");
            fail();
        } catch (CorruptedLineException e) {
        }
    }
}
