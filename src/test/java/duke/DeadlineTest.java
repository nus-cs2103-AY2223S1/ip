package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void test_toString() throws Exception {
        Todo todo = new Deadline("testTask", "2022-02-02 23:59");
        assertEquals("[D] [ ] task (by: 02-02-2022T2359", todo.toString());
    }

}
