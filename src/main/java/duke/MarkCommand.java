package duke;

public class MarkCommand extends TaskListCommand {

    MarkCommand(String cmd) {
        super(cmd);
    }

    void specialisedFunction(TaskList tasks, Ui ui, Storage storage, int taskIndex){
        boolean isChecked = tasks.fetchTask(taskIndex).check();
        if (isChecked) {
            ui.showMarked();
        } else {
            ui.showAlreadyMarked();
        }
        System.out.println(tasks.fetchTask(taskIndex));
    }
}
