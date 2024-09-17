package duke.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;
import duke.task.Todo;

public class CommandTest {
    @Test
    public void commandTest() {
        TaskList list = new TaskList();
        TaskList listComparison = new TaskList();

        //Test add task
        Command.createTask("todo borrow book", list);
        Command.createTask("todo read book", list);
        Command.createTask("todo return book", list);

        listComparison.add(new Todo("borrow book"));
        listComparison.add(new Todo("read book"));
        listComparison.add(new Todo("return book"));

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test mark task
        Command.mark("mark 1", list);
        listComparison.mark(0);

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test unmark task
        Command.unmark("unmark 1", list);
        listComparison.unmark(0);

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test delete task
        Command.delete("delete 1", list);
        listComparison.delete(0);

        assertEquals(list.getTasks(),
                listComparison.getTasks());

        //Test find task
        assertEquals(Command.find("find read book", list),
                listComparison.find("read book"));

        //Test clear task
        Command.clear(list);
        listComparison.clear();

        assertEquals(list.getTasks(),
                listComparison.getTasks());
    }
}
