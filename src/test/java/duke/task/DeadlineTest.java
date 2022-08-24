package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void test1() {
        Deadline test = new Deadline("Assignment 1", LocalDate.parse("2022-03-13"));
        assertEquals("[D] [ ] Assignment 1 (by: 2022-03-13)", test.toString());
    }

    @Test
    public void markTodoTest() {
        Deadline test = new Deadline("Project X", LocalDate.parse("2022-03-13"));
        test.mark();
        assertEquals("[D] [X] Project X (by: 2022-03-13)", test.toString());
    }
}
