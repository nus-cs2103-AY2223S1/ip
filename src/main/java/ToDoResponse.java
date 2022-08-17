public class ToDoResponse extends Response{
    private TaskList taskList;
    private String[] inputArr;

    public ToDoResponse(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    @Override
    public void action() throws DukeException{
        if (this.inputArr.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo toDo = new ToDo(this.inputArr[1]);
        this.taskList.addTask(toDo);
        super.printMessage("Got it. I've added this task: " + "\n"
                + toDo + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
    }
}