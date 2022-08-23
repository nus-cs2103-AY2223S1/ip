package duke;

import duke.command.Command;

public class Duke {

    private final Storage storage;
    private final TaskList taskList;
    private final UI ui;
    private boolean byeActivated;

    public Duke(String folderPath, String filePath) {
        this.taskList = new TaskList();
        this.ui = new UI();
        this.storage = new Storage(folderPath, filePath);
        this.byeActivated = false;
    }

    public void run() {
        ui.showWelcome();
        ui.showDividerLine();
        storage.startUpPullStorage(ui, taskList);
        while (!byeActivated && ui.hasInput()) {
            try {
                String input = ui.takeInput();
                ui.showDividerLine();
                Command c = Parser.parseInput(input);
                c.execute(taskList, ui, storage);
                byeActivated = c.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showDividerLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data", "./data/Duke.txt").run();
    }
}