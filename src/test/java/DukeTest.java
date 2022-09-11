import duke.*;
import duke.exceptions.DukeEmptyDescriptionException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeListOobException;
import duke.exceptions.DukeMissingInputException;
import duke.exceptions.DukeUnknownDateException;
import duke.exceptions.DukeUnknownInputException;
import duke.exceptions.DukeWrongInputException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void DeadlineTest() {
        Deadline test = new Deadline("test_item", "2000-01-01");
        assertEquals(test.toString(), "[D][ ] test_item by: Jan 1 2000");
        assertEquals(test.getIsCompleted(), false);
        test.toggleComplete();
        assertEquals(test.toString(), "[D][X] test_item by: Jan 1 2000");
        assertEquals(test.getIsCompleted(), true);
    }

    @Test
    public void EventTest() {
        Event test = new Event("test_item", "2000-01-01");
        assertEquals(test.toString(), "[E][ ] test_item at: Jan 1 2000");
        assertEquals(test.getIsCompleted(), false);
        test.toggleComplete();
        assertEquals(test.toString(), "[E][X] test_item at: Jan 1 2000");
        assertEquals(test.getIsCompleted(), true);
    }

    @Test
    public void TodoTest() {
        Todo test = new Todo("test_item");
        assertEquals(test.toString(), "[T][ ] test_item");
        assertEquals(test.getIsCompleted(), false);
        test.toggleComplete();
        assertEquals(test.toString(), "[T][X] test_item");
        assertEquals(test.getIsCompleted(), true);
    }

    @Test
    public void UiTest() {
        Ui test = new Ui();
        String message = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        assertEquals(test.greet(), message);

        message = "Bye. Hope to see you again soon!\n";
        assertEquals(test.exit(), message);

        ArrayList<Task> tasks= new ArrayList<Task>();
        tasks.add(new Todo("test1"));
        tasks.add(new Deadline("test2", "2000-01-01"));
        tasks.add(new Event("test3", "2000-01-01"));

        message = "Here are the list items:\n" +
                "1. [T][ ] test1\n" +
                "2. [D][ ] test2 by: Jan 1 2000\n" +
                "3. [E][ ] test3 at: Jan 1 2000\n";
        assertEquals(test.listPrint(tasks), message);

        message = "List is empty\n";
        assertEquals(test.listPrint(new ArrayList<>()), message);

        message = "OK, I've added this todo:\n" +
                "   [T][ ] test1\nNumber of tasks in list: 1\n";
        assertEquals(test.addTask("todo", tasks.get(0), 1), message);

        message = "OK, I've added this deadline:\n" +
                "   [D][ ] test2 by: Jan 1 2000\n" +
                "Number of tasks in list: 1\n";
        assertEquals(test.addTask("deadline", tasks.get(1), 1), message);

        message = "OK, I've added this event:\n" +
                "   [E][ ] test3 at: Jan 1 2000\n" +
                "Number of tasks in list: 1\n";
        assertEquals(test.addTask("event", tasks.get(2), 1), message);

        message = "OK, I've removed this task:\n" +
                "   [E][ ] test3 at: Jan 1 2000\n" +
                "Number of tasks in list: 0\n";
        assertEquals(test.deleteTask(tasks.get(2), 0), message);

        message = "OK, I've marked this task as not done yet:\n" +
                "   [E][ ] test3 at: Jan 1 2000\n";
        assertEquals(test.toggleTask(tasks.get(2)), message);

        tasks.get(2).toggleComplete();
        message = "Nice! I've marked this task as done:\n" +
                "   [E][X] test3 at: Jan 1 2000\n";
        assertEquals(test.toggleTask(tasks.get(2)), message);

        message = "Here are the list items matching test1:\n" +
                "1. [T][ ] test1\n";
        assertEquals(test.find(tasks, "test1"), message);

        message = "List is empty\n";
        assertEquals(test.find(new ArrayList<>(), "test1"), message);

        message = "List has no elements containing test4";
        assertEquals(test.find(tasks, "test4"), message);

        message = "The description for a todo cannot be empty.\n";
        assertEquals(test.printException(new DukeEmptyDescriptionException("todo")), message);

        message = "The list does not contain an entry of index -1.\n";
        assertEquals(test.printException(new DukeListOobException(-1)), message);

        message = "The additional argument for todo cannot be empty.\n";
        assertEquals(test.printException(new DukeMissingInputException("todo")), message);

        message = "The additional argument for deadline is of invalid format\n";
        assertEquals(test.printException(new DukeUnknownDateException("deadline")), message);

        message = "I'm sorry, I don't understand the command hi :(\n";
        assertEquals(test.printException(new DukeUnknownInputException("hi")), message);

        message = "The additional argument for event is of the wrong data type.\n";
        assertEquals(test.printException(new DukeWrongInputException("event")), message);
    }

    @Test
    public void TaskListTest() {
        ArrayList<Task> expected= new ArrayList<Task>();
        TaskList test = new TaskList(new ArrayList<Task>());

        assertEquals(test.getTasks().toString(), expected.toString());
        assertEquals(test.getSize(), 0);

        expected.add(new Todo("test1"));
        expected.add(new Deadline("test2", "2022-01-01"));
        expected.add(new Event("task3", "2002-12-12"));
        test.addTask(new Todo("test1"));
        test.addTask(new Deadline("test2", "2022-01-01"));
        test.addTask(new Event("task3", "2002-12-12"));
        assertEquals(test.getTasks().toString(), expected.toString());
        assertEquals(test.getSize(), 3);

        expected.remove(0);
        try {
            test.deleteTask("1");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(test.getTasks().toString(), expected.toString());
        assertEquals(test.getSize(), 2);

        expected.get(0).toggleComplete();;
        try {
            test.toggleTask("1");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(test.getTasks().toString(), expected.toString());
    }
}
