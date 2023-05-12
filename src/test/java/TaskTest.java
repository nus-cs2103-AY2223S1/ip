import duke.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TaskTest {

    @Test
    public void getStatusIcon() {
        assertEquals(" ", new Task("read book").getStatusIcon());
        assertEquals(" ", new Task("return book").getStatusIcon());
    }
}
