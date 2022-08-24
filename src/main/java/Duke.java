public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        if (storage.hasExisted()) {
            tasks = storage.load();
        } else {
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.printInitialMessage();
        while (true) {
            String command = ui.readCommand();
            switch (Parser.parse(command)) {
                case ADD:
                    return;
                case DELETE:
                    return;
                case MARK:
                    return;
                case UNMARK:
                    return;
                case QUIT:
                    Ui.printGoodbyeMessage();
                    return;
                case LIST:
                    return;
                case INVALID:
                    ui.printErrorMessage(new DukeException(
                            "OOPS!!! I'm sorry, but I don't know what that means :-("));
            }
        }
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
