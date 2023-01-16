package scottie.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.Test;

import scottie.instructions.Instruction;
import scottie.instructions.InvalidCommandException;

public class ParserTest {
    @Test
    public void testNoArguments() {
        try {
            assertEquals(Instruction.of("bye", null, Map.of()),
                    Parser.parse("bye"));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    public void testOnlyMainArgument() {
        try {
            assertEquals(Instruction.of("mark", "1", Map.of()),
                    Parser.parse("mark 1"));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    public void testFlagArguments() {
        Map<String, String> flagArgumentsMap = Map.of("flagOne", "flag arg 1", "flagTwo", "flag arg 2");
        try {
            assertEquals(Instruction.of("deadline", "main arg", flagArgumentsMap),
                    Parser.parse("deadline main arg /flagOne flag arg 1 /flagTwo flag arg 2"));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    public void testNoMainArgument() {
        Map<String, String> flagArgumentsMap = Map.of("at", "1/2/34 1200");
        try {
            assertEquals(Instruction.of("event", null, flagArgumentsMap),
                    Parser.parse("event /at 1/2/34 1200"));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    public void testFlagWithNoValue() {
        Map<String, String> flagArgumentsMap = Map.of("fOne", "", "fTwo", "arg2", "fThree", "");
        try {
            assertEquals(Instruction.of("list", null, flagArgumentsMap),
                    Parser.parse("list /fOne /fTwo arg2 /fThree"));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    public void testAllFlagsWithNoValue() {
        Map<String, String> flagArgumentsMap = Map.of("by", "", "desc", "");
        try {
            assertEquals(Instruction.of("sort", null, flagArgumentsMap),
                    Parser.parse("sort /by /desc"));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }

    @Test
    public void testExtraSpaces() {
        Map<String, String> flagArgumentsMap = Map.of("by", "12/12/12");
        try {
            assertEquals(Instruction.of("deadline", "buy   milk", flagArgumentsMap),
                    Parser.parse("   deadline   buy   milk   /by   12/12/12   "));
        } catch (InvalidCommandException e) {
            fail("An unexpected exception occurred while running this test.");
        }
    }
}
