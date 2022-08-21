public class Maria {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Maria() {

        this.ui = new Ui();
        this.storage = new Storage("tasks.mariadata");
        this.taskList = StorageConverter.stringToTasks(this.storage, this.ui);

    }

    public void run() {

        this.ui.showInstructions();

        while (true) {

            String commandStr = this.ui.readCommand();
            Command command = Parser.parse(commandStr);
            command.execute(this.taskList, this.ui, this.storage);

        }

    }

    public static void main(String[] args) {

        Maria maria = new Maria();
        maria.run();

    }

}
