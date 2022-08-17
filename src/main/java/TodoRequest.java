public class TodoRequest extends Request{
    private Todo todo;
    private TasksList tasksList;

    private static final String TO_DO_MSG = "Got it. I've added this task: \n";

    public TodoRequest(TasksList tasksList, String requestCommand) {
        this.tasksList = tasksList;
        this.todo = new Todo(requestCommand);
    }

    @Override
    public void execute() {
        this.tasksList.addToList(this.todo);
        StringBuilder sb = new StringBuilder();
        sb.append(TodoRequest.TO_DO_MSG + this.todo + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        super.printResponse(sb.toString());
    }


}
