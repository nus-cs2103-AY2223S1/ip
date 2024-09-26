package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void printWelcome_ui_welcomeMessageReturned() {
        assertEquals(Ui.printWelcome(), "Hello ! I am Duke, your task tracking assistant!");
    }

    @Test
    public void printTaskSearch_noMatchingTask_noSearchResultsMessageReturned(){
        String match = "";
        assertEquals(Ui.printTaskSearch(match), "Sorry! Cannot find any matching tasks!");
    }

    @Test
    public void printTaskSearch_withMatchingTasks_searchResultsListReturned(){
        String match = "1. [T][] Borrow books\n2. [T][X] Organize event";
        assertEquals(Ui.printTaskSearch(match), "Here are the matching tasks in your list:\n" + match);
    }
}
