package dobby;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void getStatusFromFile_stringFromFile_TaskStatus() {
        Parser parser = new Parser();
        assertEquals(parser.getStatusTxt("E | [ ] | test1 | Feb 02 2022 00:00"), false);
    }
    @Test
    public void getDateFromUser_stringFromUser_TaskDate() {
        Parser parser = new Parser();
        assertEquals(parser.getDate("test /at 2022-07-07 2005"), "Jul 07 2022 20:05");
    }
    @Test
    public void getCmdFromUser_stringFromUser_TaskDate() {
        Parser parser = new Parser();
        assertEquals(parser.getTaskType("deadline deadlineTest /by 2022-07-07 2005"), "deadline");
    }
    @Test
    public void getCorrectDateFormat_stringOfDate_dateWithCorrectFormat() {
        Parser parser = new Parser();
        assertEquals(parser.dateFormat("2022-07-07 2005", "yyyy-MM-dd HHmm", "MMM dd yyyy HH:mm"), "Jul 07 2022 20:05");
    }
}
