public class Maria {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    public Maria() {

        this.ui = new Ui();
        this.storage = new Storage("tasks.mariadata");
        this.taskList = StorageConverter.stringToTasks(this.storage);

    }

    public void run() {

        while (true) {

            String commandStr = this.ui.readCommand();
            Command command = Parser.parse(commandStr);
            command.execute(this.taskList, this.ui, this.storage);

        }

    }

    private String makeTodo() {
        return TaskTodo.makeTodo(this.scanner, this.taskList);
    }

    private String makeDeadline() {
        return TaskDeadline.makeDeadline(this.scanner, this.taskList);
    }

    private String makeEvent() {
        return TaskEvent.makeEvent(this.scanner, this.taskList);
    }

}
