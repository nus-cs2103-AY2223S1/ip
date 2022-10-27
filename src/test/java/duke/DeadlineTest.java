package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void toStringTest() {

        LocalDate tempDate = LocalDate.parse("24/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Deadline temp1 = new Deadline("", false, tempDate, PriorityLevel.Priority.LOW);

        assertEquals("[D][ ]  (by: Jan 24 2022) (Priority: LOW)", temp1.toString());

        Deadline temp2 = new Deadline("read book", false, tempDate, PriorityLevel.Priority.LOW);

        assertEquals("[D][ ] read book (by: Jan 24 2022) (Priority: LOW)", temp2.toString());

        temp2.mark();

        assertEquals("[D][X] read book (by: Jan 24 2022) (Priority: LOW)", temp2.toString());
    }


}
