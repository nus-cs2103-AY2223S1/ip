package jean.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void getIsDone_normalInputFalse_success() {
        assertFalse(new Todo("something").getIsDone());
    }

    @Test
    public void getStatusIcon_normalInputFalse_success() {
        assertEquals(" ", new Todo("something").getStatusIcon());
    }
}
