import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke (String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws DukeException, IOException {
        ui.greetings();
        Parser parser = new Parser(tasks);
        parser.readInput();
    }


    public static void main(String[] args) throws Exception {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }





}
