package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void test1() {
        Event test = new Event("Assignment 1", LocalDate.parse("2022-03-13"));
        assertEquals("[E] [ ] Assignment 1 (at: 2022-03-13)", test.toString());
    }

    @Test
    public void markTodoTest() {
        Event test = new Event("Project X", LocalDate.parse("2022-03-13"));
        test.mark();
        assertEquals("[E] [X] Project X (at: 2022-03-13)", test.toString());
    }
}
