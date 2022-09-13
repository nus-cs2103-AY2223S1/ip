package duke;

import duke.tasks.ToDos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    @Test
    public void test() {
        assertEquals("[T][ ] homework", new ToDos("homework").toString());
    }
}
