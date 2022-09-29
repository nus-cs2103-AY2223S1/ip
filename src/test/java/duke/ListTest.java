package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;

public class ListTest {
    private Ui ui;
    private StorageList storageList;

    @BeforeEach
    public void setUp() {
        storageList = new StorageList();
        ui = new Ui(storageList);
        try {
            ui.setLastInput("todo borrow book");
            ui.setLastCommand("todo");
            new AddCommand().execute(ui, storageList);

            ui.setLastInput("deadline return book /by 2019-10-15 1800");
            ui.setLastCommand("deadline");
            new AddCommand().execute(ui, storageList);

            ui.setLastInput("event project meeting /at Mon 2-4pm");
            ui.setLastCommand("event");
            new AddCommand().execute(ui, storageList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void listTest() {
        String actualList = storageList.toString();
        String expectedList = "\t 1.[T][ ] borrow book\n"
                + "\t 2.[D][ ] return book (by: 18:00, Tue, Oct 15 2019)\n"
                + "\t 3.[E][ ] project meeting (at: Mon 2-4pm)\n";
        assertEquals(expectedList, actualList);
    }

    public void deleteTest() {
        ui.setLastInput("delete 1");
        ui.setLastCommand("delete");
        try {
            new DeleteCommand().execute(ui, storageList);
            String actualList = storageList.toString();
            String expectedList = "\t 1.[D][ ] return book (by: 18:00, Tue, Oct 15 2019)\n"
                    + "\t 2.[E][ ] project meeting (at: Mon 2-4pm)\n";
            assertEquals(expectedList, actualList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
