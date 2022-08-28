package jude;

import java.io.IOException;

// Test template adapted from https://se-education.org/guides/tutorials/junit.html
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests functionality of Deadline class.
 */
public class ParserTest {

    /**
     * Test Case 1: Tests conversion of valid dates.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate1() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("1 Jan 2022");
    }

    /**
     * Test Case 2: Tests conversion of valid dates.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate2() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("Jan 4 2022");
    }

    /**
     * Test Case 3: Tests conversion of valid dates.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate3() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("2022-04-14");
    }

    /**
     * Test Case 4: Tests conversion of valid dates.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate4() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("23 Aug 2022");
    }

    /**
     * Test Case 5: Tests conversion of valid dates.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate5() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("Jul 15 2022");
    }

    /**
     * Test Case 6: Tests conversion of invalid dates.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate6() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        try {
            parser.convertToDate("Apr 32 2022");
            Assertions.fail();
        } catch (IllegalCommandException ex) {
            return;
        }
    }

    /**
     * Test Case 7: Tests conversion of nonsensical strings.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate7() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        try {
            parser.convertToDate("Monday");
            Assertions.fail();
        } catch (IllegalCommandException ex) {
            return;
        }
    }

    /**
     * Test Case 8: Tests for null string.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate8() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        try {
            parser.convertToDate(null);
            Assertions.fail();
        } catch (IllegalCommandException ex) {
            return;
        }
    }

    /**
     * Test Case 9: Test 12-hour clock.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate_withTime1() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("22 Aug 2022 3:01 am");
    }

    /**
     * Test Case 10: Test 24-hour clock.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate_withTime2() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("2022-08-23 13:01");
    }

    /**
     * Test Case 11: Test invalid 12-hour clock.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate_invalidTime1() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        try {
            parser.convertToDate("22 Aug 2022 13:01 am");
            Assertions.fail();
        } catch (IllegalCommandException ex) {
            return;
        }
    }

    /**
     * Test Case 12: Test invalid 24-hour clock.
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate_invalidTime2() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        try {
            parser.convertToDate("Aug 24 2022 24:01");
            Assertions.fail();
        } catch (IllegalCommandException ex) {
            return;
        }
    }

    /**
     * Test Case 13: Test valid ISO Date
     *
     * @throws IOException When system I/O fails.
     */
    @Test
    public void testConvertToDate_isoDate() throws IOException {
        Parser parser = new Parser(new TaskListStub(), new StorageStub("testing.txt"));
        parser.convertToDate("2022-02-28T22:31");
    }
}
