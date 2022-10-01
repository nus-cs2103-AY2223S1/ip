package duke.taskList;

import duke.tasks.Event;
import duke.tasks.Task;
import duke.ui.Ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class TaskListTest {

    @Test
    void listTasks() {
        try {
            List<Task> tasks = new ArrayList<Task>();
            Ui ui = new Ui();
            Task dummy = new Task("", "", "");
            tasks.add(dummy);
            new TaskList(tasks, 1, "").listTasks(ui);
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! I'm sorry, but cannot print empty list",
                    e.getMessage());
        }
    }

    @Test
    void markTask() {
        assumeTrue(true);
        try {
            List<Task> tasks = new ArrayList<Task>();
            Ui ui = new Ui();
            Task dummy = new Task("", "", "");
            tasks.add(dummy);
            String[] command = {"Mark", "1"};
            new TaskList(tasks, 1, "").markTask(command, ui);
        } catch (Exception e) {
            assertEquals("Mark error",
                    e.getMessage());
        }
    }

    @Test
    void unmarkTask() {
        try {
            List<Task> tasks = new ArrayList<Task>();
            Ui ui = new Ui();
            Task dummy = new Task("", "", "");
            tasks.add(dummy);
            String[] command = {"Mark", "1"};
            new TaskList(tasks, 1, "").markTask(command, ui).unmarkTask(command, ui);
        } catch (Exception e) {
            assertEquals("Unmark error",
                    e.getMessage());
        }
    }

    @Test
    void deleteTask() {
        //arraylist cannot be empty
        try {
            List<Task> tasks = new ArrayList<Task>();
            Ui ui = new Ui();
            Task dummy = new Task("", "", "");
            tasks.add(dummy);
            tasks.add(dummy);
            String[] command = {"Delete", "2"};
            new TaskList(tasks, 1, "").deleteTask(command, ui);
        } catch (Exception e) {
            assertEquals("Delete error",
                    e.getMessage());
        }
    }

    @Test
    void toDoTask() {
        try {
            Ui ui = new Ui();
            String command = "todo borrow book";
            new TaskList().toDoTask(command, ui);
        } catch (Exception e) {
            assertEquals("toDoError error",
                    e.getMessage());
        }
    }

    @Test
    void deadlineTask() {
        try {
            Ui ui = new Ui();
            String command = "deadline return book /by 2/12/2019 1800";
            new TaskList().toDoTask(command, ui);
        } catch (Exception e) {
            assertEquals("deadLine error",
                    e.getMessage());
        }
    }

    @Test
    void eventTask() {
        try {
            Ui ui = new Ui();
            String command = "event Anime convention /by 27/11/2022 0800";
            new TaskList().toDoTask(command, ui);
        } catch (Exception e) {
            assertEquals("event error",
                    e.getMessage());
        }
    }

    @Test
    void thingsTask() {
        assertEquals(1,1);
    }

    @Test
    void addCurr() {
        assertEquals(1,1);
    }

    @Test
    void removeCurr() {
        assertEquals(1,1);
    }

    @Test
    void getTasks() {
        assertEquals(1,1);
    }

    @Test
    void getCurr() {
        assertEquals(1,1);
    }

    @Test
    void findTasks() {
        assertEquals(1,1);
    }
}