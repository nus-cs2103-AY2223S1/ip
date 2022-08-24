import java.io.IOException;

public class Duke {
    
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());

        } catch (IOException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }


    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = parser.parse(input);
                command.execute(tasks, ui, storage, input);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args)  {
        new Duke("data/Duke.txt").run();
    
    }

    
}
