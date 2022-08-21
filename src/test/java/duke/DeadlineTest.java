package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    private Deadline generateCorrectDeadline() {
        return new Deadline("something",
                1, "2019-12-31", "12:34");
    }

    @Test
    public void getStatusIcon_markedTask_returnX() {
        assertEquals(generateCorrectDeadline().getStatusIcon(),
                "X");
    }

    @Test
    public void parseToSaveData_correctInput_correctParsedOutput() {
        assertEquals("D|1|something|2019-12-31|12:34",
                generateCorrectDeadline().parseToSaveData());
    }

    @Test
    public void testToString() {
        assertEquals(generateCorrectDeadline().toString(),
                "[D][X] something (by: Dec 31 2019 12:34 PM)");
    }
}
