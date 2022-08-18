public class TodoRequest extends Request{
    private String[] inputArray;
    private TasksList tasksList;

    private static final String TO_DO_MSG = "Got it. I've added this task:\n";

    public TodoRequest(TasksList tasksList, String[] inputArray) {
        this.tasksList = tasksList;
        this.inputArray = inputArray;
    }

    @Override
    public void execute() throws DukeException {
        if (this.inputArray.length < 2) {
            throw new DukeException("The description of a todo cannot be empty!");
        }

        String description = this.inputArray[1];
        //create a todo object
        Todo todo = new Todo(description);

        this.tasksList.addToList(todo);
        StringBuilder sb = new StringBuilder();
        sb.append(TodoRequest.TO_DO_MSG + todo + "\n" + "Now you have ");
        if (this.tasksList.getLength() <= 1) {
            sb.append(this.tasksList.getLength() + " task in the list.\n");
        } else {
            sb.append(this.tasksList.getLength() + " tasks in the list.\n");
        }
        super.printResponse(sb.toString());
    }
}
