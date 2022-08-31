package alpha.command;

import alpha.FileOperations;
import alpha.TaskList;
import alpha.Ui;
import alpha.task.Deadline;
import alpha.task.Event;
import alpha.task.Task;

import java.io.IOException;
public class Add extends Command {

    private Task task;

    public Add(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList taskList, Ui uI, FileOperations fileOperations) throws IOException {
        taskList.addToTaskList(task);
        String textToAppend;
        if (this.task instanceof Event) {
            Event event = (Event) task;
            textToAppend = event.getTaskType() + " | " + event.getStatus() + " | "
                    + event.getDescription() + "| on " + event.getDate() + "\n";
        } else if (this.task instanceof Deadline){
            Deadline deadline = (Deadline) task;
            textToAppend = deadline.getTaskType() + " | " + deadline.getStatus() + " | "
                    + deadline.getDescription() + "| by " + deadline.getDeadline() + "\n";
        } else {
            textToAppend = task.getTaskType() + " | " + task.getStatus() + " | " + task.getDescription() + "\n";
        }
        fileOperations.writeToFile(textToAppend);
        uI.colouredPrint(uI.ANSI_BLUE, ">> " + "added: " + task.getDescription());
    }
}
