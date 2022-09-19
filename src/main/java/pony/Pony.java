package pony;

import pony.command.Command;

public class Pony {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Pony.
     * @param filePath File path of the memory file.
     */
    public Pony(String filePath) {
        assert filePath != null : "File path should not be empty";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskList());
    }

    public String runCommand(String command) {
        Command c = Parser.parseCommand(command);
        String message = c.execute(tasks, storage, ui);
        return message;
    }

}