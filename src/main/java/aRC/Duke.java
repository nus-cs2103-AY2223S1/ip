package arc;

public class Duke {
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;
    private UI ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(this.storage.load());
        this.ui = new UI();
        this.parser = new Parser(this.storage, this.tasklist, this.ui);
    }

    public static void main(String[] args) {
        new Duke("data/aRC.txt").run();
    }

    public void run() {
        this.ui.hello();
        String input = this.ui.readInput();

        // Keeps reading user input until the user types "bye"
        while(!input.equals("bye")) {
            try {
                this.parser.parse(input);
            } catch (DukeException e) {
                this.ui.printException(e);
            }
            input = this.ui.readInput();
        }

        this.ui.bye();
    }
}
