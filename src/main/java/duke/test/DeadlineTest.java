package duke.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;



class DeadlineTest {
    @Test
    void toString_deadlineTask() {
        assertEquals("[D][ ] buy bread (by: 12 Dec 2020 | Sat | 6:00PM)",
                new Deadline("buy bread", "2020-12-12 1800").toString());
    }

    @Test
    void toStringStorage_deadlineTask() {
        assertEquals("D | 0 | buy bread | 12 Dec 2020 | Sat | 6:00PM",
                new Deadline("buy bread", "2020-12-12 1800").toStringStorage());
    }
}
