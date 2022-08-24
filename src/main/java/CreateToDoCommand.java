public class CreateToDoCommand extends Command {

    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in your ToDo task:");
        String todo = ui.inputText();
        try {
            controller.checkTaskContent(todo);
            ToDo todoTask = new ToDo(todo);
            controller.addToList(todoTask);
            ui.display(todoTask.toString(), false, false, false, false);
        } catch (EmptyContentException ece) {
            ui.reportError("No empty task is allowed! Please try again...");
        }
    }
}
