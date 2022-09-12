package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {

    @Test
    public void parseCommand_deadlineCommand_stringArray() {
        assertArrayEquals(new String[]{"D", "complete homework", "2022-08-22"},
                new Parser().parseCommand("deadline complete homework /by 2022-08-22"));
        assertArrayEquals(new String[]{"D", "watch k-drama", "2030-10-01"},
                new Parser().parseCommand("deadline watch k-drama /by 2030-10-01"));
        assertArrayEquals(new String[]{"D", "eat mala hotpot", "2022-01-22"},
                new Parser().parseCommand("deadline eat mala hotpot /by 2022-01-22"));
    }

    @Test
    public void parseCommand_deleteCommand_stringArray() {
        assertArrayEquals(new String[]{"d", "3"}, new Parser().parseCommand("delete 3"));
        assertArrayEquals(new String[]{"d", "7"}, new Parser().parseCommand("delete 7"));
        assertArrayEquals(new String[]{"d", "12"}, new Parser().parseCommand("delete 12"));
    }
}
