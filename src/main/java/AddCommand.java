import java.io.IOException;

public class AddCommand extends Command {
    private int taskType;
    private String taskDetails;
    public AddCommand(int taskType, String taskDetails) {
        // 0: TODO Task
        // 1: DEADLINE Task
        // 2: EVENT Task
        this.taskType = taskType;
        this.taskDetails = taskDetails;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (this.taskType == 0) {
            tasks.add(new Todo(this.taskDetails));
        } else if (this.taskType == 1) {
            String[] infoArray = this.taskDetails.split(" /by ", 2);
            tasks.add(new Deadline(infoArray[0], infoArray[1]));
        } else {
            String[] infoArray = this.taskDetails.split(" /at ", 2);
            tasks.add(new Event(infoArray[0], infoArray[1]));
        }
        String taskDescription = "  " + tasks.getTask(tasks.size() - 1).toString();
        System.out.println(this);
        System.out.println(taskDescription);
        storage.writeToFile(tasks);
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "__________________________________________________\n"
                + "Got it. I have added this task:" ;
    }
}
