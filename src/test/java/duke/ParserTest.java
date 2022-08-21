package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void parseInput_unknownInput_InvalidInputExceptionThrown() {
        assertThrows(InvalidDukeInputException.class,
                () -> Parser.parseInput("uselessInfo"));
    }

    @Test
    void parseInput_forbiddenCharacter_BannedExceptionThrown() {
        assertThrows(BannedDukeCharacterException.class,
                () -> Parser.parseInput("Something with | in it"));
    }

    @Test
    void parseInput_correctDateline_success() {
        assertArrayEquals(new String[] {"deadline", "test", "2000-12-31", "00:00", "", ""},
                Parser.parseInput("deadline test /by 2000-12-31 00:00"));
    }

    @Test
    void parseInput_badDateline_successWithoutCrash() {
        assertArrayEquals(new String[] {"deadline", "test", "2000-12-31", "", "", ""},
                Parser.parseInput("deadline test /by 2000-12-31"));
    }
}