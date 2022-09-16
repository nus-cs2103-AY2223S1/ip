package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void testArgumentParser_onlyKeyword() {
        Parser.ParsedInputArguments parsedArguments = Parser.getInputArguments("hello");
        Parser.ParsedInputArguments expectedArguments = new Parser.ParsedInputArguments("hello", "");

        assertEquals(parsedArguments.keyword, expectedArguments.keyword);
        assertEquals(parsedArguments.args, expectedArguments.args);
        assertEquals(parsedArguments.flags, expectedArguments.flags);
    }

    @Test
    void testArgumentParser_keywordWithArgs1() {
        Parser.ParsedInputArguments parsedArguments =
                Parser.getInputArguments("hello args");
        Parser.ParsedInputArguments expectedArguments =
                new Parser.ParsedInputArguments("hello", "args");

        assertEquals(parsedArguments.keyword, expectedArguments.keyword);
        assertEquals(parsedArguments.args, expectedArguments.args);
        assertEquals(parsedArguments.flags, expectedArguments.flags);
    }

    @Test
    void testArgumentParser_keywordWithArgs2() {
        Parser.ParsedInputArguments parsedArguments =
                Parser.getInputArguments("hello args args");
        Parser.ParsedInputArguments expectedArguments =
                new Parser.ParsedInputArguments("hello", "args args");

        assertEquals(parsedArguments.keyword, expectedArguments.keyword);
        assertEquals(parsedArguments.args, expectedArguments.args);
        assertEquals(parsedArguments.flags, expectedArguments.flags);
    }

    @Test
    void testArgumentParser_keyWordWithOneFlag() {
        Parser.ParsedInputArguments parsedArguments =
                Parser.getInputArguments("hello args /at 2pm");
        Parser.ParsedInputArguments expectedArguments =
                new Parser.ParsedInputArguments("hello", "args");
        expectedArguments.flags.put("/at", "2pm");

        assertEquals(parsedArguments.keyword, expectedArguments.keyword);
        assertEquals(parsedArguments.args, expectedArguments.args);
        assertEquals(parsedArguments.flags, expectedArguments.flags);

    }

    @Test
    void testArgumentParser_keyWordWithMultipleFlag() {
        Parser.ParsedInputArguments parsedArguments =
                Parser.getInputArguments(
                        "hello args /at 2pm /by 1 2 3 4 5 /hello greetings"
                );
        Parser.ParsedInputArguments expectedArguments =
                new Parser.ParsedInputArguments("hello", "args");
        expectedArguments.flags.put("/at", "2pm");
        expectedArguments.flags.put("/by", "1 2 3 4 5");
        expectedArguments.flags.put("/hello", "greetings");

        assertEquals(parsedArguments.keyword, expectedArguments.keyword);
        assertEquals(parsedArguments.args, expectedArguments.args);
        assertEquals(parsedArguments.flags, expectedArguments.flags);
    }
}
