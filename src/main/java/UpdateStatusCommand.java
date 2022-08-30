import java.io.IOException;

public class UpdateStatusCommand extends Command {

    private int idx;
    private boolean isDone;

    public UpdateStatusCommand(int idx, boolean isDone) {
        this.idx = idx;
        this.isDone = isDone;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        tasks.markTask(idx, isDone);
        try {
            storage.rewriteFile(tasks);
            StringBuilder markMessage = new StringBuilder();
            if (isDone) {
                markMessage.append("\tNice! I've marked this task as done:\n");
            } else {
                markMessage.append("\tOK, I've marked this task as not done yet:\n");
            }
            markMessage.append("\t\t" + tasks.getTask(idx).toString());
            ui.print(markMessage);
        } catch (IOException e) {
            tasks.markTask(idx, !isDone);
            throw new PlutoException("\tOOPS!!! Couldn't update task status. Try again!");
        }
    }

}
