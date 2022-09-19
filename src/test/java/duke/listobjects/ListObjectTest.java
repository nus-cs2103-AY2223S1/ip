package duke.listobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListObjectTest {

    @Test
    public void testSwitchStatus(){
        ListObject ls = new ListObject("finish testing", "2022-10-10 18:00", 1);
        assertEquals("[X] ", ls.showStatusIndicator());
        ls.switchStatus();
        assertEquals("[ ] ", ls.showStatusIndicator());
        ls.switchStatus();
        assertEquals("[X] ", ls.showStatusIndicator());
    }

    @Test
    public void testValidDateTimeFormat(){
        ListObject dl = new Deadline("finish testing", 1, "2022-10-10 18:00");
        String actual = dl.formatDateTime(ListObject.Type.DEADLINE);
        String expected = " (by: Oct 10 2022 at 18:00:00)";
        assertEquals(expected, actual);

        ListObject ev = new Event("finish testing", 1, "2022-10-10 18:00 19:00");
        String act = ev.formatDateTime(ListObject.Type.EVENT);
        String exp = " (on: Oct 10 2022 from: 18:00:00 to: 19:00:00)";
        assertEquals(exp, act);
    }

    @Test
    public void testComparison(){
        ListObject ls1 = new ListObject("finish testing", "2022-10-10 18:00", 1);
        ListObject ls2 = new ListObject("finish testing", "2022-10-10 18:00", 1);
        ListObject ls3 = new ListObject("finish testing", "2022-10-10 19:00", 0);
        ListObject ls4 = new ListObject("ask questions", "2022-10-10 19:00", 1);
        ListObject ls5 = new ListObject("enjoy vacations", "2022-12-12 18:00", 0);
        assertEquals(0, ls1.compareTo(ls2));
        assertEquals(-1, ls1.compareTo(ls3));
        assertEquals(-5, ls4.compareTo(ls3));
        assertEquals(2, ls5.compareTo(ls2));
    }
}
