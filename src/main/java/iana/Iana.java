package iana;

import iana.command.Command;
import iana.exception.IanaException;
import iana.parser.Parser;
import iana.storage.Storage;
import iana.tasks.TaskList;
import iana.ui.Ui;

/**
 * Represents the command line interface Iana used to manage tasks.
 */
public class Iana {
    private TaskList tasks;
    private Ui ui;

    public Iana() {
        tasks = new TaskList();
        ui = new Ui();
        try {
            Storage.initialise();   
            tasks = Storage.load();
        } catch (IanaException e) {
            ui.say(e.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui);
        } catch (IanaException e) {
            return e.getMessage();
        }
    }
}