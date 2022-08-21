package duke.test;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        assertEquals("[D][ ] buy bread (by: 12 Dec 2020 | Sat | 6:00PM)",
                new Deadline("buy bread", "2020-12-12 1800").toString());
    }

    @Test
    void toStringData() {
        assertEquals("D | 0 | buy bread | 12 Dec 2020 | Sat | 6:00PM",
                new Deadline("buy bread", "2020-12-12 1800").toStringData());
    }
}