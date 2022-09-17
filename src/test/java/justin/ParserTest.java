package justin;

import justin.command.Command;
import justin.task.Deadline;
import justin.task.ToDo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parserTest1() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        String output = "";
        try {
            Storage storage = new Storage("justin.txt");
            String msg = "todo homework";
            Command c = Parser.parse(msg);
            output = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            System.out.println(""); //to handle DukeException
        }
        assertEquals("Got it, I have added the following into the list: \n\n" +
                "T | Undone | homework\n" +
                "You now have 1 task in your list.", output);
    }

    @Test
    public void parserTest2() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        String output = "";
        try {
            Storage storage = new Storage("justin.txt");
            String msg = "deadline assignment /by 2022-10-12 23:59";
            Command c = Parser.parse(msg);
            output = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            System.out.println(""); //to handle DukeException
        }
        assertEquals("Got it, I have added the following into the list: \n\n" +
                "D | Undone | assignment | Oct 12 2022 1159PM\n" +
                "You now have 1 task in your list.", output);
    }

    @Test
    public void parserTest3() {
        TaskList taskList = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        String output = "";
        try {
            Storage storage = new Storage("justin.txt");
            String msg = "event meeting /at 2022-10-12 11:00";
            Command c = Parser.parse(msg);
            output = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            System.out.println(""); //to handle DukeException
        }
        assertEquals("Got it, I have added the following into the list: \n\n" +
                "E | Undone | meeting | Oct 12 2022 1100AM\n" +
                "You now have 1 task in your list.", output);
    }

    @Test
    public void parserTest4() {
        ToDo task = new ToDo("homework", false);
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(task);
        Ui ui = new Ui();
        String output = "";
        try {
            Storage storage = new Storage("justin.txt");
            String msg = "list";
            Command c = Parser.parse(msg);
            output = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            System.out.println(""); //to handle DukeException
        }
        assertEquals("Here are the tasks in your list: \n\n" +
                "1. T | Undone | homework", output);
    }

    @Test
    public void parserTest5() {
        ToDo task1 = new ToDo("homework", false);
        Deadline task2 = new Deadline("assignment", false, "2022-10-12", "23:59");
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(task1);
        taskList.addTask(task2);
        Ui ui = new Ui();
        String output = "";
        try {
            Storage storage = new Storage("justin.txt");
            String msg = "mark 2";
            Command c = Parser.parse(msg);
            output = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            System.out.println(""); //to handle DukeException
        }
        assertEquals("Nice! I have marked the following task(s) as done: \n\n" +
                "1. D | Done! | assignment | Oct 12 2022 1159PM\n", output);
    }
}
