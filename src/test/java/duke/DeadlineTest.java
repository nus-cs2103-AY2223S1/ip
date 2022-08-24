package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testToStorageFormat() {
        assertEquals("D | 0 | watch lecture | 2021-12-12", new Deadline("watch lecture", "2021-12-12").toStorageFormat());
    }
}
