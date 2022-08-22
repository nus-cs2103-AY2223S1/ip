import java.io.IOException;

import java.time.format.DateTimeParseException;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean end;

    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
            this.tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            this.ui.printException(e);
        } catch (IOException e) {
            this.ui.printException(e);
            this.tasks = new TaskList();
        }
    }

    private void run() {
        this.ui.printIntro();
        while (!this.end) {
            try {
                String line = this.ui.nextLine();
                Command command = Parser.parse(line);
                this.ui.printLine();
                command.run(this.tasks, this.ui, this.storage);
                this.end = command.isEnd();
            } catch (DukeException | IOException | DateTimeParseException e) {
                this.ui.printException(e);
            } finally {
                this.ui.printLine();
            }
        }
        this.ui.close();
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}