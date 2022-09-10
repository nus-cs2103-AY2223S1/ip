package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.Todo;

public class DukeListTest {
    private DukeList list;
    private Task task;

    @BeforeEach
    public void setUp() {
        list = new DukeList();
        task = new Todo("borrow book");
    }

    @Test
    public void getListSize_emptyList_returnZero() {
        assertEquals(0, list.getListSize());
    }

    @Test
    public void getListSize_oneTask_returnOne() {
        list.add(task);
        assertEquals(1, list.getListSize());
    }

    @Test
    public void add_validTask_addsSuccessfully() {
        String expectedStr = "Got it. I've added this task:\n"
                + task
                + "\nNow you have 1 task in the list.";

        assertEquals(expectedStr, list.add(task));
    }

    @Test
    public void done_taskMarkedDone() {
        try {
            list.add(task);
            String expectedStr = "Nice! I've marked this task as done:\n"
                    + "[T][X] borrow book";
            assertEquals(expectedStr, list.done(1));
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void delete_listSizeZero() {
        list.add(task);
        assertEquals(1, list.getListSize());
        try {
            String expectedStr = "Noted. I've removed this task:\n"
                    + task
                    + "\nNow you have 0 tasks in the list.";
            assertEquals(expectedStr, list.delete(1));
            assertEquals(0, list.getListSize());
        } catch (DukeException e) {
            fail(e);
        }
    }

    @Test
    public void search_book_taskFound() {
        list.add(task);
        String expectedStr = "Here are the matching tasks in your list:\n"
                + "1. "
                + task;
        assertEquals(expectedStr, list.search("book"));
    }

    @Test
    public void search_meet_nothingFound() {
        String expectedStr = "Nothing was found.";
        assertEquals(expectedStr, list.search("meet"));
    }
}
