package duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

public class AddTest {
    private Ui ui;
    private StorageList storageList;

    @BeforeEach
    public void setUp() {
        storageList = new StorageList();
        ui = new Ui(storageList);
    }

    @Test
    public void addTodoTest() {
        ui.setLastInput("todo borrow book");
        ui.setLastCommand("todo");
        try {
            new AddCommand().execute(ui, storageList);
            assert (storageList.get(0) instanceof Todo && storageList.get(0).toString().equals("[T][ ] borrow book"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addTodoShortcutTest() {
        ui.setLastInput("t borrow book");
        ui.setLastCommand("t");
        try {
            new AddCommand().execute(ui, storageList);
            assert (storageList.get(0) instanceof Todo && storageList.get(0).toString().equals("[T][ ] borrow book"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addDeadlineTest() {
        ui.setLastInput("deadline return book /by 2019-10-15 1800");
        ui.setLastCommand("deadline");
        try {
            new AddCommand().execute(ui, storageList);
            assert (storageList.get(0) instanceof Deadline
                    && storageList.get(0).toString().equals("[D][ ] return book (by: 18:00, Tue, Oct 15 2019)"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addEventTest() {
        ui.setLastInput("event project meeting /at Mon 2-4pm");
        ui.setLastCommand("event");
        try {
            new AddCommand().execute(ui, storageList);
            assert (storageList.get(0) instanceof Event
                    && storageList.get(0).toString().equals("[E][ ] project meeting (at: Mon 2-4pm)"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addTodoEmptyTest() {
        ui.setLastInput("todo");
        ui.setLastCommand("todo");
        try {
            new AddCommand().execute(ui, storageList);
        } catch (DukeException e) {
            assert (e.getMessage().equals("☹ OOPS!!! The description of a todo cannot be empty."));
        }
    }

    @Test
    public void addDeadlineEmptyTest() {
        ui.setLastInput("deadline");
        ui.setLastCommand("deadline");
        try {
            new AddCommand().execute(ui, storageList);
        } catch (DukeException e) {
            assert (e.getMessage().equals("☹ OOPS!!! The description of a deadline cannot be empty."));
        }
    }

    @Test
    public void addEventEmptyTest() {
        ui.setLastInput("event");
        ui.setLastCommand("event");
        try {
            new AddCommand().execute(ui, storageList);
        } catch (DukeException e) {
            assert (e.getMessage().equals("☹ OOPS!!! The description of a event cannot be empty."));
        }
    }
}
