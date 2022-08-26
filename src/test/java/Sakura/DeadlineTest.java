package Sakura;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void testStringifyTask() {
        Deadline test1 = new Deadline("submit iP task1", "2022-05-15 2359");
        assertEquals("D|0|submit iP task1|2022-05-15 2359", test1.stringifyTask());
    }

    @Test
    void testMarkDone() {
        Deadline test2 = new Deadline("submit iP task2", "2022-05-15 2359");
        test2.markDone();
        assertEquals("D|1|submit iP task2|2022-05-15 2359", test2.stringifyTask());
    }

    @Test
    void testToString() {
        Deadline test1 = new Deadline("submit iP task1", "2022-05-15 2359");
        assertEquals("\u001B[31m(DEADLINE)\u001B[0m[ ] submit iP task1 (by: 23:59, 15 May 2022)", test1.toString());

    }
}
