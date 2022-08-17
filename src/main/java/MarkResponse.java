public class MarkResponse extends Response{
    private TaskList taskList;
    private String input;

    public MarkResponse(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    @Override
    public void action() {
        int index = Integer.parseInt(this.input.split(" ", 2)[1]) - 1;
        if (this.taskList.markDone(index)) {
            super.printMessage("Nice! I've marked this task as done:" + "\n"
                    + this.taskList.getTask(index) + "\n");
        } else {
            super.printMessage("Invalid task!" + "\n");
        }
    }
}
