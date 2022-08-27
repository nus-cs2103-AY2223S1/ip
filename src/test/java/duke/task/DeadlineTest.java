package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void summary_success() {
        Deadline d1 = new Deadline("eat", "wed 2022-10-13 2359", true);
        Deadline d2 = new Deadline("eat", "wed 2022-10-1322400", true);
        Deadline d3 = new Deadline("eat", "wed 2022-13-1str1900", false);
        Deadline d4 = new Deadline("eat", "wed 2022-13-132400", false);
        String s1 = "D | 1 | eat | wed 2022-10-13 2359";
        String s2 = "D | 1 | eat | wed 2022-10-1322400";
        String s3 = "D | 0 | eat | wed 2022-13-1str1900";
        String s4 = "D | 0 | eat | wed 2022-13-132400";
        assertEquals(s1, d1.summary());
        assertEquals(s2, d2.summary());
        assertEquals(s3, d3.summary());
        assertEquals(s4, d4.summary());
    }

    @Test
    public void toString_success() {
        Deadline d1 = new Deadline("eat", "wed 2022-10-13 2359", true);
        Deadline d2 = new Deadline("eat", "wed 2022-10-1322401", true);
        Deadline d3 = new Deadline("eat", "wed 2022-13-1str1900", false);
        Deadline d4 = new Deadline("eat", "wed 2022-13-132401", false);
        String s1 = "[D] [=^._.^=] eat nya! (by: Oct 13 2022, 23:59)";
        String s2 = "[D] [=^._.^=] eat nya! (by: Oct 13 2022)";
        String s3 = "[D] [Zzzzzzz] eat nya! (by: 19:00)";
        String s4 = "[D] [Zzzzzzz] eat nya! (by: wed 2022-13-132401)";
        assertEquals(s1, d1.toString());
        assertEquals(s2, d2.toString());
        assertEquals(s3, d3.toString());
        assertEquals(s4, d4.toString());
    }
}
