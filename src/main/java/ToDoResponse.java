public class ToDoResponse extends Response{
    private TaskList taskList;
    private String input;

    public ToDoResponse(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    @Override
    public void action() {
        ToDo toDo = new ToDo(input);
        this.taskList.addTask(toDo);
        super.printMessage("Got it. I've added this task: " + "\n"
                + toDo + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
    }
}