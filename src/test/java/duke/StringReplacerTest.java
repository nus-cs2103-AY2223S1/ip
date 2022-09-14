package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringReplacerTest {
    @Test
    public void StringReplacerTest() {
        assertEquals("Assignment", new StringReplacer().
                replaceAll(" Deadline Assignment ", "Deadline"));
    }
}
