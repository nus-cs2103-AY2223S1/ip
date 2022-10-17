package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testToString() {
        assertEquals("[E][ ] test1 (at: 07:30PM Oct 27 2022)",
                new Event("test1", false, LocalDateTime.of(2022, 10, 27, 19, 30)).toString());
        assertEquals("[E][X] test2 (at: 05:47AM Feb 01 2023)",
                new Event("test2", true, LocalDateTime.of(2023, 2, 1, 5, 47)).toString());
    }

    @Test
    void testTaskToDataString() {
        Event test3 = new Event("test3", false, LocalDateTime.of(2022, 8, 28, 23, 31));
        assertEquals("E | X | test3 | 28/08/2022 2331\n", test3.taskToDataString());

        Event test4 = new Event("test4", false, LocalDateTime.of(2023, 3, 11, 2, 59));
        assertEquals("E | X | test4 | 11/03/2023 0259\n", test4.taskToDataString());
    }
}
