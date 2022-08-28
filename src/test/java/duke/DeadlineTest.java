package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {

        LocalDate tempDate = LocalDate.parse("24/01/2022", DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Deadline temp1 = new Deadline("", false, tempDate);

        assertEquals("[D][ ]  (by: Jan 24 2022)", temp1.toString());

        Deadline temp2 = new Deadline("read book", false, tempDate);

        assertEquals("[D][ ] read book (by: Jan 24 2022)", temp2.toString());

        temp2.mark();

        assertEquals("[D][X] read book (by: Jan 24 2022)", temp2.toString());
    }


}
