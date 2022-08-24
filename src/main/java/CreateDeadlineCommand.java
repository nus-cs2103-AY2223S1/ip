public class CreateDeadlineCommand extends Command {

    public void execute(TasksController controller, Ui ui, Storage storage) {
        ui.prompt("Please type in your Deadline task:");
        String ddl = ui.inputText();
        try {
            controller.checkTaskContent(ddl);
            ui.prompt("Please type in deadline (yyyy-mm-dd HH:MM):");
            String ddlTime = ui.inputText();
            controller.checkTimeFormat(ddlTime);
            Deadline ddlTask = new Deadline(ddl, ddlTime);
            controller.addToList(ddlTask);
            ui.display(ddlTask.toString(), false, false, false, false);
        } catch (EmptyContentException ece) {
            ui.reportError("No empty task is allowed! Please try again...");
        } catch (InvalidTimeException ite) {
            ui.reportError("The time format is invalid! Please try again...");
        }
    }
}
