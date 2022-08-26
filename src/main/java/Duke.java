import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.printError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while(!isExit) {
            try{
                String fullCommand = ui.getInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                ui.printError(e);
            } catch (IOException e) {
                ui.printTab("Can't save to duke.txt. There is an invalid data, please edit accordingly");
            } catch (Exception e) {
                try {
                    storage.updateData(tasks);
                } catch (IOException e1) {
                    e1.getMessage();
                }

            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}