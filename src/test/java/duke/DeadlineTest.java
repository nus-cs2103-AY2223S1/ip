package duke;

import duke.Deadline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineString() {
        Deadline d = new Deadline("soccer", "2022-09-08","9pm", false);
        assertEquals("[D][O]soccer (by: Sep 8 2022 9pm)",d.toString());

    }

}
