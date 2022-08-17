public class ListResponse extends Response{
    private TaskList taskList;

    public ListResponse(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void action() {
        super.printMessage(this.taskList.toString());
    }
}
