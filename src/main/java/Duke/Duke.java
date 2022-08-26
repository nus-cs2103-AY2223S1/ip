package Duke;


public class Duke {

    private static TaskList taskList;
    private static Ui ui;
    private static Storage storage;
    protected String filePath;


    public Duke(String filePath) {
        ui = new Ui();
        this.taskList = new TaskList();
        this.filePath = filePath;
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.loadingError();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("/Users/yiye/Desktop/cs2103Projects/ip/Data.txt");
        Parser parser = new Parser(duke, ui);
        duke.run(parser);
    }

    public void run(Parser parser)  {
        ui.greet();
        parser.run();
        storage.writeTasks(taskList.listTasks());
        ui.stop();
    }

}
