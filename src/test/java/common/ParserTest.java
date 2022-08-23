package common;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testIsValidDatetime() {
        assertTrue(Parser.isValidDatetime("1/12/2000 1600", "d/MM/uuuu HHmm"));
        assertFalse(Parser.isValidDatetime("1-12-2000 1600", "d/MM/uuuu HHmm"));
        assertFalse(Parser.isValidDatetime("fail test", "d/MM/uuuu HHmm"));
    }

    @Test
    public void testSplitArrayIntoSubstrings() {
        List<String> expectedOutput = Arrays.asList("hello", "world");
        List<String> methodOutput = Parser.splitArrayIntoSubstrings(new String[] {"hello", "/by", "world"}, "/by");
        assertArrayEquals(expectedOutput.toArray(), methodOutput.toArray());
    }
}
