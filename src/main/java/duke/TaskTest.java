package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void test_create_todo() {
        try {
            String[] arr = new String[]{ "todo", "test" };
            Task todoOne = Task.createTask(arr);
            Task todoTwo = new Todo("test");
            assertEquals(todoOne.toString(), todoTwo.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_mark_todo() {
        try {
            String[] arr = new String[]{ "todo", "test" };
            Task todoOne = Task.createTask(arr);
            todoOne.markAsDone();
            Task todoTwo = new Todo("test");
            todoTwo.markAsDone();
            assertEquals(todoOne.toString(), todoTwo.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
