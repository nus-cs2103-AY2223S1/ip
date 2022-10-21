package duke.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.ui.BotUI;


public class InputCheckerTest {

    private static final BotUI UI = new BotUI();
    @Test
    public void testToDoInput_invalidToDo_exceptionThrown() {
        try {
            InputChecker.checkInput("todo");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testEventInput_eventWithoutTime_exceptionThrown() {
        try {
            InputChecker.checkInput("event dummyEvent /at");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testEventInput_eventWithoutDetail_exceptionThrown() {
        try {
            InputChecker.checkInput("event /at 2022-01-01 1900");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testEventInput_eventWithDoubleAt_exceptionThrown() {
        try {
            InputChecker.checkInput("event /at /at");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testEventInput_onlyEvent_exceptionThrown() {
        try {
            InputChecker.checkInput("event");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testDeadlineInput_deadlineWithoutTime_exceptionThrown() {
        try {
            InputChecker.checkInput("deadline dummyEvent /by");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testDeadlineInput_deadlineWithoutDetail_exceptionThrown() {
        try {
            InputChecker.checkInput("deadline /at 2011-01-01 1900");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testDeadlineInput_deadlineWithDoubleBy_exceptionThrown() {
        try {
            InputChecker.checkInput("deadline /by /by");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testDeadlineInput_onlyDeadline_exceptionThrown() {
        try {
            InputChecker.checkInput("deadline");
        } catch (DukeException ex) {
            assertEquals(UI.invalidFormat(), ex.getMessage());
        }
    }

    @Test
    public void testCorrectInput_correctInput_nothingHappen() {
        try {
            InputChecker.checkInput("deadline dummyDeadline /by 2022-08-25 1700");
            InputChecker.checkInput("todo dummyToDo");
            InputChecker.checkInput("event dummyEvent /at 2022-08-25 1700");
            assertEquals("pass", "pass");
        } catch (DukeException ex) {
            System.out.println("fail");
        }
    }
}
