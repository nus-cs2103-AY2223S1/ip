package duke.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;
import duke.task.Todo;

public class ParserTest {
    @Test
    public void parseTest() {
        TaskList list = new TaskList();
        TaskList listComparison = new TaskList();

        //Test add task
        Parser.parse("todo borrow book", list);
        Parser.parse("todo read book", list);
        Parser.parse("todo return book", list);

        listComparison.add(new Todo("borrow book"));
        listComparison.add(new Todo("read book"));
        listComparison.add(new Todo("return book"));

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test mark task
        Parser.parse("mark 1", list);
        listComparison.mark(0);

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test unmark task
        Parser.parse("unmark 1", list);
        listComparison.unmark(0);

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test delete task
        Parser.parse("delete 1", list);
        listComparison.delete(0);

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test clear task
        Parser.parse("clear", list);
        listComparison.clear();

        assertEquals(list.getTasks(),
                listComparison.getTasks());
    }
}
