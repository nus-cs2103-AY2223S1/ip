public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    
    public Duke(String filepath) throws Exception {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } finally {
            System.out.println();
        }
        //} catch (DukeException e) {
        //    tasks = new TaskList();
        //}
    }

    public void run() throws Exception {
        ui.greet();
        Parser parser = new Parser(tasks);
        boolean terminate = false;
        while (!terminate) {
            String input = ui.getInput();
            ui.printFrontSpacing();
            terminate = parser.parse(input);
            storage.store(tasks.getList());
            if (terminate == true) {
                ui.sayBye();
            }
            ui.printBackSpacing();
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke("./data/duke.txt").run();
    }
}