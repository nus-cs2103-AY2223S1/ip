import amanda.task.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class DeadlineTest {

    @Test
    void getBy() {
        Deadline d = new Deadline("eat", "22-07-2023-23:59");
        assertEquals("22-07-2023-23:59", d.getBy());
    }
}