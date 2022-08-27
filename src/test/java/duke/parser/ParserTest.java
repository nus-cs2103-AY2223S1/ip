package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_todo_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("todo"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'todo ABC' to add task ABC\n";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void parse_event_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("event eat /by tonight"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'event ABC /at DATE' to add event ABC on DATE\n";
            assertEquals(msg, e.getMessage());
        }
    }


    @Test
    public void parse_deadline_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("deadline eat /at tonight"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'deadline ABC /by DATE' to add deadline ABC due by DATE\n";
            assertEquals(msg, e.getMessage());
        }
    }


    @Test
    public void parse_mark_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("mark"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'mark x' to mark task x as complete\n";
            assertEquals(msg, e.getMessage());
        }
    }


    @Test
    public void parse_unmark_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("unmark eat"));
            fail();
        } catch (Exception e) {
            String INTRO = "Nyat a valid instruction! Rub my belly instead!\n";
            String TODO = "Input 'todo ABC' to add task ABC\n";
            String EVENT = "Input 'event ABC /at DATE' to add event ABC on DATE\n";
            String DEADLINE = "Input 'deadline ABC /by DATE' to add deadline ABC due by DATE\n";
            String MARK = "Input 'mark xxx' to mark task xxx as complete\n";
            String UNMARK = "Input 'unmark xxx' to mark task xxx as incomplete\n";
            String DELETE = "Input 'delete xxx' to delete task xxx from the list\n";
            String LIST = "Input 'list' for overview\n";
            String BYE = "Input 'bye' to exit.\n";
            String OUTRO = "NYAAAAAA!\n";
            String TAB = "    ";
            String msg = INTRO
                    + TAB + TODO
                    + TAB + EVENT
                    + TAB + DEADLINE
                    + TAB + MARK
                    + TAB + UNMARK
                    + TAB + DELETE
                    + TAB + LIST
                    + TAB + BYE
                    + TAB + OUTRO;
            assertEquals(msg, e.getMessage());
        }
    }




    @Test
    public void parse_delete_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("delete"));
            fail();
        } catch (Exception e) {
            String msg = "Input 'delete x' to delete task x from the list\n";
            assertEquals(msg, e.getMessage());
        }
    }

    @Test
    public void parse_invalid_exceptionThrown() {
        try {
            assertEquals(0, new Parser().parse("blabla eat /by tonight"));
            fail();
        } catch (Exception e) {
            String INTRO = "Nyat a valid instruction! Rub my belly instead!\n";
            String TODO = "Input 'todo ABC' to add task ABC\n";
            String EVENT = "Input 'event ABC /at DATE' to add event ABC on DATE\n";
            String DEADLINE = "Input 'deadline ABC /by DATE' to add deadline ABC due by DATE\n";
            String MARK = "Input 'mark xxx' to mark task xxx as complete\n";
            String UNMARK = "Input 'unmark xxx' to mark task xxx as incomplete\n";
            String DELETE = "Input 'delete xxx' to delete task xxx from the list\n";
            String LIST = "Input 'list' for overview\n";
            String BYE = "Input 'bye' to exit.\n";
            String OUTRO = "NYAAAAAA!\n";
            String TAB = "    ";
            String msg = INTRO
                    + TAB + TODO
                    + TAB + EVENT
                    + TAB + DEADLINE
                    + TAB + MARK
                    + TAB + UNMARK
                    + TAB + DELETE
                    + TAB + LIST
                    + TAB + BYE
                    + TAB + OUTRO;
            assertEquals(msg, e.getMessage());
        }
    }



}

