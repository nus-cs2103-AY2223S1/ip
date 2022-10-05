import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.tasks.Todo;

public class TodoTest {

    @Test
    public void newTodoTest() {
        assertEquals(new Todo("Read a book").toString(), "[T][ ] Read a book");
        assertEquals(new Todo("Return book").toString(), "[T][ ] Return book");
    }

    @Test
    public void markTodoTest() {
        Todo readBook = new Todo("Read a book");
        Todo returnBook = new Todo("Return book");
        readBook.mark();
        returnBook.mark();
        assertEquals(readBook.toString(), "[T][X] Read a book");
        assertEquals(returnBook.toString(), "[T][X] Return book");
    }

    @Test
    public void unmarkTodoTest() {
        Todo readBook = new Todo("Read a book");
        Todo returnBook = new Todo("Return book");
        readBook.mark();
        readBook.unmark();
        returnBook.mark();
        returnBook.unmark();
        assertEquals(readBook.toString(), "[T][ ] Read a book");
        assertEquals(returnBook.toString(), "[T][ ] Return book");
    }

}
