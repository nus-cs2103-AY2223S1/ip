
public class UnmarkResponse extends Response{
    private TaskList taskList;
    private String input;

    public UnmarkResponse(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    @Override
    public void action() {
        int index = Integer.parseInt(this.input.split(" ", 2)[1]) - 1;
        if (this.taskList.unmarkDone(index)) {
            super.printMessage("OK, I've marked this task as not done yet:" + "\n"
                    + this.taskList.getTask(index) + "\n");
        } else {
            super.printMessage("Invalid task!" + "\n");
        }
    }
}
