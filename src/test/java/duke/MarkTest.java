package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.task.Todo;


public class MarkTest {
    private Ui ui;
    private StorageList storageList;

    @BeforeEach
    public void setUp() {
        storageList = new StorageList();
        ui = new Ui(storageList);
        ui.setLastInput("todo borrow book");
        ui.setLastCommand("todo");
        try {
            new AddCommand().execute(ui, storageList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void markTest() {
        try {
            ui.setLastInput("mark 1");
            ui.setLastCommand("mark");
            new MarkCommand().execute(ui, storageList);
            assert (storageList.get(0) instanceof Todo && storageList.get(0).toString().equals("[T][X] borrow book"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void unmarkTest() {
        try {
            ui.setLastInput("mark 1");
            ui.setLastCommand("mark");
            new MarkCommand().execute(ui, storageList);
            ui.setLastInput("unmark 1");
            ui.setLastCommand("unmark");
            new UnmarkCommand().execute(ui, storageList);
            assert (storageList.get(0) instanceof Todo && storageList.get(0).toString().equals("[T][ ] borrow book"));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void indexOutOfBoundTest() {
        String expected = "\t____________________________________________________________\n"
                + "\t ☹ OOPS!!! Invalid index.\n"
                + "\t____________________________________________________________";
        try {
            ui.setLastInput("mark 100");
            ui.setLastCommand("mark");
            new MarkCommand().execute(ui, storageList);
        } catch (DukeException e) {
            assertEquals(expected, e.getMessage());
        }
    }
}
