public class ListRequest extends Request{
    private TasksList tasksList;

    public ListRequest(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    @Override
    public void execute() {
        super.printResponse(this.tasksList.toString());
    }
}
