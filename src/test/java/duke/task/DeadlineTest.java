package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void encodeTest(){
        Deadline deadline = new Deadline("homework", LocalDate.parse("2022-01-02"));
        assertEquals("D" + Task.ENCODING_SEPARATOR + "2022-01-02" + Task.ENCODING_SEPARATOR +
                        "homework" + Task.ENCODING_SEPARATOR+ "false",
                deadline.encode());
        deadline = new Deadline("homework", LocalDate.parse("2022-01-02"), true);
        assertEquals("D" + Task.ENCODING_SEPARATOR + "2022-01-02" + Task.ENCODING_SEPARATOR +
                        "homework" + Task.ENCODING_SEPARATOR+ "true",
                deadline.encode());
    }

    @Test
    public void toStringTest(){
        Deadline deadline = new Deadline("homework", LocalDate.parse("2022-01-02"));
        assertEquals("[D][ ] homework (by: 2022-01-02)",
                deadline.toString());
        deadline = new Deadline("homework", LocalDate.parse("2022-01-02"), true);
        assertEquals("[D][X] homework (by: 2022-01-02)",
                deadline.toString());
    }
}