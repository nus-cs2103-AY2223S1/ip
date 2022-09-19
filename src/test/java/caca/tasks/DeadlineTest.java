package caca.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import caca.TaskList;
import caca.exceptions.InvalidDateException;

/**
 * Test class for Deadline.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class DeadlineTest {

    /**
     * Tests the behaviour of adding a deadline with duplicates.
     *
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    @Test
    public void addDeadlineTest_withDuplicate_warning() throws InvalidDateException {
        TaskList deadlineList = new TaskList(null);

        Deadline deadline = new Deadline("revision", "23/09/2022 2359");
        TaskList.addTask(deadline);
        assertEquals(1, deadlineList.getTasks().size());

        Deadline duplicateDeadline = new Deadline("revision", "23/09/2022 2359");
        String addDuplicateDeadline = TaskList.addTask(duplicateDeadline);
        assertEquals(1, deadlineList.getTasks().size());

        assertEquals("OOPS!!! (*_*)\n"
                + "Duplicate task:\n"
                + "[D][ ] revision (by: Sep 23 2022 23:59)\n"
                + "This is not added again.",
                addDuplicateDeadline);
    }

    /**
     * Tests the behaviour of adding a deadline with a valid date and time.
     *
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    @Test
    public void deadlineDate_validDate_success() throws InvalidDateException {
        Deadline deadline = new Deadline("quiz 1", "01/09/2022 1600");
        assertEquals("[D][ ] quiz 1 (by: Sep 01 2022 16:00)",
                deadline.toString());
    }

    /**
     * Tests the behaviour of adding a deadline with an invalid day as date.
     */
    @Test
    public void deadlineDate_invalidDay_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("quiz 2", "00/09/2022 1600");
            fail(); // Test should not reach this line.
        } catch (InvalidDateException e) {
            assertEquals("OOPS!!! (*_*)\n"
                    + "You have keyed in an invalid date and time!\n"
                    + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                    + "E.g. 24/08/2022 2359",
                    e.getMessage());
        }
    }

    /**
     * Tests the behaviour of adding a deadline with an invalid time as date.
     */
    @Test
    public void deadlineDate_invalidTime_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("quiz 3", "01/09/2022 2401");
            fail(); // Test should not reach this line.
        } catch (InvalidDateException e) {
            assertEquals("OOPS!!! (*_*)\n"
                    + "You have keyed in an invalid date and time!\n"
                    + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                    + "E.g. 24/08/2022 2359",
                    e.getMessage());
        }
    }

    /**
     * Tests the behaviour of converting a deadline that is marked as done into file format.
     *
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    @Test
    public void toFileFormat_deadlineDone_success() throws InvalidDateException {
        Deadline deadline = new Deadline("quiz 4", "01/09/2022 1600", true);
        assertEquals("D | X | quiz 4 | 01/09/2022 1600",
                deadline.toFileFormat());
    }

    /**
     * Tests the behaviour of converting a deadline that is not marked as done into file format.
     *
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    @Test
    public void toFileFormat_deadlineUndone_success() throws InvalidDateException {
        Deadline deadline = new Deadline("quiz 5", "01/09/2022 1600", false);
        assertEquals("D |   | quiz 5 | 01/09/2022 1600",
                deadline.toFileFormat());
    }

}
