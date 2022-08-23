public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            this.ui.show(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.showGreeting();
        boolean isBye = false;
        while (!isBye) {
            try {
                String input = this.ui.readInput();
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.ui, this.storage);
                this.storage.save(this.tasks);
                isBye = command.isBye();
            } catch (DukeException e) {
                this.ui.show(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
