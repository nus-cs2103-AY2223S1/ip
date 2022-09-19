package duke;

import storage.Storage;
import task.TaskList;
import task.NotesList;
import ui.Ui;
import exception.DukeException;
import parser.Parser;
import command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private NotesList notes;
    private Ui ui;

    public Duke(String filePath) {
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?\n");
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
            notes = new NotesList(storage.loadNotes());
            ui = new Ui();
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Parser parser = new Parser(fullCommand);
                Command c = parser.parse(fullCommand);
                c.execute(tasks, notes, ui, storage);
                isExit = c.isExit();
                if (!isExit) {
                    ui.changeCommand();
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) {
        Parser parser = new Parser(input);
        Command c = parser.parse(input);
        String response = c.execute(tasks, notes, ui, storage);
        return response;
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}

