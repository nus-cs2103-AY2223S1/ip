package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.command.AddCommand;


public class MatchingTest {
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
    public void matchDateTest() {
        String input = "date 2019-10-15 1800";
        String command = "date";
        try {
            String actualList = storageList.toString(
                    LocalDateTime.parse(Parser.findFirstCommand(input, command), Ui.getInputFormatter()));
            String expectedList = "\t 1.[D][ ] return book (by: 18:00, Tue, Oct 15 2019)\n";
            assertEquals(expectedList, actualList);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void matchRegexTest() {
        String actualList = storageList.toString("book");
        String expectedList = "\t 1.[T][ ] borrow book\n"
                + "\t 2.[D][ ] return book (by: 18:00, Tue, Oct 15 2019)\n";
        assertEquals(expectedList, actualList);
    }
}
