import jarvis.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toDataFormTest(){
        Deadline ddl = new Deadline("ddl", "2011-11-11T11:22", true);
        assertEquals("D|1|ddl|2011-11-11T11:22\n", ddl.toDataForm());
    }

    @Test
    public void toStringTest() {
        Deadline ddl = new Deadline("ddl /by 2002-11-11 22:22", false);
        assertEquals("[D][ ] ddl (by: 11 Nov 2002 22:22)", ddl.toString());
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}
