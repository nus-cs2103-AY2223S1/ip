package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {


    @Test
    public void test_toString() throws Exception {
        Deadline todo = new Deadline("test", LocalDateTime.parse("2022-02-02 23:59", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
        assertEquals("[D] [ ] test (by: 02-02-2022T2359", todo.toString());
    }

}
