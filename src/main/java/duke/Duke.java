package duke;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("OOPS! I have issue creating a new file!");
        }
    }

    public static void main(String[] args) {
        //Run bot
        new Duke("src/main/tasks.txt").run();
    }

    public void run() {
        ui.hello();
        new Parser(tasks, storage).start();
        ui.bye();
    }
}

